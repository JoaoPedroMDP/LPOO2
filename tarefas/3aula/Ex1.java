import java.util.Arrays;
import java.util.stream.Stream;

public class Ex1 {
    
    public static Integer[] unionArr(Integer[] arr1, Integer[] arr2) {
        return Stream.of(arr1, arr2) //stream dos parâmtros da função
        .flatMap(Stream::of) //diminui a recorrencia de streams dentro de streams
        .distinct() //devolve somente ocorrências diferentes no array final
        .toArray(Integer[]::new);
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 5, 45};
        Integer[] arr2 = {2, 5, 7, 15};

        System.out.println(Arrays.toString(unionArr(arr1, arr2)));
    }
}
