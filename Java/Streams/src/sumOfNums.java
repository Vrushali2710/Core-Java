import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sumOfNums {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

    }
}
