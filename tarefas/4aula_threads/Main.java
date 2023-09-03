
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.time.Instant;
import java.time.Duration;

public class Main{
    ArrayList<ArrayList<Long>> timeData = new ArrayList<ArrayList<Long>>();

    public static void somaNormal(ArrayList<ArrayList<Integer>> matrix){
        Instant comecoSoma = Instant.now();
        int somaTotal = 0;
        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.get(i).size(); j++){
                somaTotal += matrix.get(i).get(j);
            }
        }
        System.out.println("Tempo soma normal: " + Main.diffTimes(comecoSoma));
        System.out.println("Soma total (normal): " + somaTotal);
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> matrix){
        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.get(i).size(); j++){
                System.out.printf("%2d ", matrix.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static Long diffTimes(Instant startTime){
        Instant endTime = Instant.now();
        return Duration.between(startTime, endTime).toNanos()/10000;
    }

    public static void main(String args[]){
        // Instancio a matriz
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        System.out.println("\nMatriz " + rows + "x" + cols + ":");
        // Insiro uns numeros aleatorios
        for(int i = 0; i < rows; i++){
            ArrayList<Integer> linha = new ArrayList<Integer>();
            for(int j = 0; j < cols; j++){
                linha.add(1);
            }
            matrix.add(linha);
        }

        // Agora vou criar um array de completablefutures onde vou armazenar todas as funções a serem paralelizadas
        CompletableFuture<Integer>[] todasAsLinhas = new CompletableFuture[rows] ;
        // E então, preencher esse array com as funções
        
        for(int i = 0; i < rows; i++){
            final int index = i;
            todasAsLinhas[i] = CompletableFuture.supplyAsync(
                () -> {
                    Integer somaLinha = 0;
                        for(Integer numero : matrix.get(index)){
                            somaLinha += numero;
                        }

                    return somaLinha;
                });
        }


        // Esse completable aqui vai terminar apenas depois que todos os outros que passei por parâmetro terminarem. 
        // Uso isso pra poder garantir que tudo será executado.
        Instant somaParalelizada = Instant.now();
        CompletableFuture<Void> todasAsSomas = CompletableFuture.allOf(todasAsLinhas);
        try{
            // Dou um get nele pra garantir que tudo terminou
            todasAsSomas.get();
        }catch(InterruptedException|ExecutionException e){
            e.printStackTrace();
        }

        int somaTotal = 0;
        // Agora é só passar de completable em completable pra pegar os resultados
        // Dou .get() aqui mas eles já terminaram com o .get ali de cima, é só que .get é a maneira que preciso usar pra pegar o resultado
        for(CompletableFuture<Integer> somaLinha : todasAsLinhas){
            try{
                somaTotal += somaLinha.get();
            }catch(InterruptedException|ExecutionException e){
                e.printStackTrace();
            }
        }
        System.out.println("Tempo de soma paralelizada: " + Main.diffTimes(somaParalelizada));
        System.out.println("Soma total: " + somaTotal);

        Main.somaNormal(matrix);
    }
}