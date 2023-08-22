import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Ex2{
    public static Integer totalPares(Integer[] ints){
        return Stream.of(ints)
            .reduce(0, (total, num) -> {
                if(num % 2 == 0){
                    return total + 1;
                }
                return total;
            }); 
    }

    public static Integer maiorValor(List<Integer> ints){
        return ints.stream()
            .max(Integer::compare).get();
    }

    public static void main(String[] args){
        Integer ex2TotalPares = totalPares(new Integer[]{1,2,3,4,5,6,7,8,9,10});
        System.out.println(String.format("Total de pares: %s", String.valueOf(ex2TotalPares)));

        try{
            Integer ex2MaiorValor = maiorValor(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
            System.out.println(String.format("maior valor: %s", String.valueOf(ex2MaiorValor)));
        }catch(NoSuchElementException e){
            System.out.println("Não foi possível encontrar o maior valor");
        }
    }
}