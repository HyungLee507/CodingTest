import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static int threshold;
    static long[] numbers;
    static int minLength = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        initData();
        //
        int start = 0;
        int end = 1;
        long sum = 0;
        while (start < end) {
            sum = numbers[end] - numbers[start];
            if (sum < threshold && end + 1 <= size) {
                end++;
                continue;
            }
            if (sum >= threshold && start + 1 <= size) {
                minLength = Integer.min(end - start, minLength);
                start++;
                continue;
            }
            start++;
        }
        if (minLength == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(minLength);


    }

    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        size = Integer.parseInt(st.nextToken());
        threshold = Integer.parseInt(st.nextToken());
        numbers = new long[size + 1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= size; i++) {
            numbers[i] = numbers[i - 1] + Integer.parseInt(st.nextToken());
        }
    }

}