import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int arraySize = Integer.parseInt(st.nextToken());
        int iterationCount = Integer.parseInt(st.nextToken());
        int[] values = new int[arraySize];

        st = new StringTokenizer(bf.readLine());
        values[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < arraySize; i++) {
            values[i] = Integer.parseInt(st.nextToken()) + values[i - 1];
        }

        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken());
            intervalSum(values, startIndex, endIndex);
        }

    }

    private static void intervalSum(int[] values, int startIndex, int endIndex) {
        int intervalSum;
        if (startIndex == 1) {
            intervalSum = values[endIndex - 1];
        } else {
            intervalSum = values[endIndex - 1] - values[startIndex - 2];
        }
        System.out.println(intervalSum);
    }

}