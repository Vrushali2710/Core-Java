import java.util.ArrayList;
import java.util.List;

public class TraditionalVsStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        //Traditional: Find sum of squares of even numbers
        int sumOfSquares = 0;
        for(int number: numbers){
            if(number % 2 ==0){
                int square = number * number;
                sumOfSquares += square;
            }
        }
        System.out.println("sum of squares(traditional) :"+sumOfSquares);

        int sumOfSquaresStream = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(number -> number * number)
                .sum();
        System.out.println("sum of squares(stream) :"+sumOfSquaresStream);
    }
}
