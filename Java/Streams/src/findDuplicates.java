import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class findDuplicates {

    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1,2,3,4,5,6,6,7,7);
        Set<Integer> str = new HashSet<>();
        List<Integer> ls2 = ls.stream().filter(i -> !str.add(i)).toList();
        System.out.println(ls2);

    }
}
