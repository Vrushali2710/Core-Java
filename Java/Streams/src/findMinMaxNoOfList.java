import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class findMinMaxNoOfList {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,8,4,3,56,7);
        Integer i = list1.stream().min(Comparator.comparing(Integer::intValue)).get();
        System.out.println(i);

        Integer j = list1.stream().max(Comparator.comparing(Integer::intValue)).get();
        System.out.println(j);
    }
}
