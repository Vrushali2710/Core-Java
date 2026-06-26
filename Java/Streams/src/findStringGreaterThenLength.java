import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class findStringGreaterThenLength {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("abfdggg","ddhjjdh","hhhgd","hd");
        long no = list.stream().filter(i -> i.length() > 4).count();
        System.out.println(no);
        Set<String> set =  list.stream().filter(i -> i.length() > 4).collect(Collectors.toSet());
        System.out.println(set);

    }
}
