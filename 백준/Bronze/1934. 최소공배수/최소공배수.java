import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int iterationCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int[] numbers = new int[2];
            numbers[0] = Integer.parseInt(st.nextToken());
            numbers[1] = Integer.parseInt(st.nextToken());
            Arrays.sort(numbers);
            int greatestCommonDivisor = useEuclidean(numbers[0], numbers[1]);
            int leastCommonMultiple = numbers[0] * numbers[1] / greatestCommonDivisor;
            System.out.println(leastCommonMultiple);
        }


    }

    private static int useEuclidean(int small, int big) {

        int number = big % small;
        if (number == 0) {
            return small;
        }
        return useEuclidean(number, small);
    }


}