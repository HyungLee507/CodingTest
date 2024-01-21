import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(bf.readLine());
        numbers = new int[numberCount];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < numberCount; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        int compareNumberCounts = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < compareNumberCounts; i++) {
            int comparingNumber = Integer.parseInt(st.nextToken());
            bSearch(comparingNumber);
        }
        System.out.println(sb);

    }

    private static int bSearch(int comparingNumber) {

        int high = numbers.length - 1;
        int low = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] == comparingNumber) {

                sb.append(1);
                sb.append('\n');
                return 1;
            }
            if (numbers[mid] < comparingNumber) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        sb.append(0);
        sb.append('\n');
        return 0;
    }


}