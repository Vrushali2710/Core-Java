import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class findAverageOfSquaredNums {
//    Square the list of numbers and then filter out the numbers greater than 10 and then find the average of filtered numbers
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6);
        double avg = nums.stream().mapToInt(i -> i * i).filter(i -> i >10).average().getAsDouble();
        System.out.println(avg);

    }
}
