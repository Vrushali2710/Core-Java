import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Map_Vs_FlatMap {

    public static void main(String[] args) {



        List<String> words = Arrays.asList("Hello","World");
        // Map is used to perform any transformation
        // Using Map to convert each word to uppercase
        List<String> uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Using map:  "+uppercaseWords);

        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5,6),
                Arrays.asList(7,8,9)
        );
        // FlatMap is used wherever you want to perform flattening and transformation
        // Using FlatMap to flatten the nested list
        List<Integer> flattenedList = nestedList.stream()
                .flatMap(List::stream)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("Flattened List:"+flattenedList);

    }
}
