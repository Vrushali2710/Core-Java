import java.util.Arrays;

public class Parallel_Stream {
    public static void main(String[] args) {
        int[] intArray = new int[100000];
        Arrays.fill(intArray,2);
        // Sequential
        long startTime = System.currentTimeMillis();
        long sum = Arrays.stream(intArray)
                .mapToLong(n -> n * n).sum();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

        //Parallel
        long startTimeP = System.currentTimeMillis();
        long sum2 = Arrays.stream(intArray).parallel()
                .mapToLong(n -> n * n).sum();
        long endTimeP = System.currentTimeMillis();
        System.out.println(endTimeP-startTimeP);
    }
}
