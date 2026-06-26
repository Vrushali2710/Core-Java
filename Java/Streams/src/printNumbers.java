import java.util.Random;
import java.util.stream.IntStream;

public class printNumbers {

    public static void main(String[] args) {
        Random random = new Random();
        random.ints(5,1,6).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        IntStream.range(1,6).forEach(System.out::println);
    }
}

