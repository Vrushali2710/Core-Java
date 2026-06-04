import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Streams_Example2 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,8,9,10);
        List<Integer> squaredNumbers = numbers.stream()
                .filter(n -> n%2 ==0)
                .distinct()
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println("Squared Numbers:"+squaredNumbers);

        long count = numbers.stream().filter(n -> n>5).distinct()
                .count();

        System.out.println("count of numbers:"+count);
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("sum of numbers:"+sum);
    }
}
