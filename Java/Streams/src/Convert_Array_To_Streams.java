import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Convert_Array_To_Streams {

    public static void main(String[] args) {
        //Primitive type array
        int[] arrayInt = {1,2,3,4,5};
        IntStream streamObj = Arrays.stream(arrayInt);
        streamObj.forEach(System.out::println);//terminal function

        //Object type array
        Integer[] arrayInteger ={6,7,8,9};
        Stream<Integer> objStream = Stream.of(arrayInteger);
        objStream.forEach(System.out::println);//terminal function

    }
}
