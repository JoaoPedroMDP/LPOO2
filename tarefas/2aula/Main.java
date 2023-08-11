public class Main {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa("Albert Einstein", 14, 3, 1879);
        Pessoa p2 = new Pessoa("Isaac Newton", 4, 1, 1642);

        CalculaIdadeFunction calculaIdadeFunction = (Pessoa pessoa) -> {
            pessoa.calculaIdade();  
            return pessoa.informaIdade();
        };
        
        System.out.println(p1.informaNome() + " teria " + calculaIdadeFunction.calculaIdade(p1) + " anos hoje.");
        System.out.println(p2.informaNome() + " teria " + calculaIdadeFunction.calculaIdade(p2) + " anos hoje.");
    }
}

interface CalculaIdadeFunction {
    Integer calculaIdade(Pessoa pessoa);
}



