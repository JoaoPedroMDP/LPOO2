import java.time.LocalDate;

public class Pessoa{
    public String nome;
    public Integer idade;
    public Integer diaNasc;
    public Integer mesNasc;
    public Integer anoNasc;

    public Pessoa(String nome, Integer diaNasc, Integer mesNasc, Integer anoNasc){
        this.nome = nome;
        this.diaNasc = diaNasc;
        this.mesNasc = mesNasc;
        this.anoNasc = anoNasc;
    }

    public void calculaIdade(){
        LocalDate dataAtual = LocalDate.now();
        Integer diaAtual = dataAtual.getDayOfMonth();
        Integer mesAtual = dataAtual.getMonthValue();
        Integer anoAtual = dataAtual.getYear();

        Integer idade = anoAtual - this.anoNasc;

        if(mesAtual < this.mesNasc || (mesAtual == this.mesNasc && diaAtual < this.diaNasc)){
            idade--;
        }
        
        this.idade = idade;
    }

    public Integer informaIdade(){
        return this.idade;
    }

    public String informaNome(){
        return this.nome;
    }

    public void ajustaDataDeNascimento(Integer diaNasc, Integer mesNasc, Integer anoNasc){
        this.diaNasc = diaNasc;
        this.mesNasc = mesNasc;
        this.anoNasc = anoNasc;
    }

}