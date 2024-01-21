import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] numbers;

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
            System.out.println(bSearch(comparingNumber));
        }


    }

    private static int bSearch(int comparingNumber) {

        int high = numbers.length - 1;
        int low = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] == comparingNumber) {
                return 1;
            }
            if (numbers[mid] < comparingNumber) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return 0;
    }


}