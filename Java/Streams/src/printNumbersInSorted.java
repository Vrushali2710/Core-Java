import java.util.Random;
import java.util.stream.IntStream;

public class printNumbersInSorted {
    public static void main(String[] args) {
        Random random = new Random();
        random.ints(10,1,10).sorted().distinct().forEach(System.out::println);
    }
}
