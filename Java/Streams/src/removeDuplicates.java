import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class removeDuplicates {
    public static void main(String[] args) {
        List<Integer>  list = Arrays.asList(1,2,3,4,5,6,6,7,8);
        Set<Integer> set = new HashSet<>();
       List<Integer> list1 = list.stream().filter(set::add).toList();
        System.out.println(list1);
    }
}
