import java.util.stream.Stream;

public class concatTwoStreams {

    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(1,2,3,4,5,6);
        Stream<Integer>stream2 = Stream.of(3,5,6,7,8,8);
        Stream<Integer>str = Stream.concat(stream1,stream2);
        System.out.println(str.toList());
    }
}
