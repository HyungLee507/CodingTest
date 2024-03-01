import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int numberCount = Integer.parseInt(st.nextToken());
        int minimumPrintCount = Integer.parseInt(st.nextToken());
        int numbersSize = 2;
        int temp = numberCount;
        while (temp > 0) {
            temp = temp / 2;
            numbersSize = numbersSize * 2;
        }
        numbers = new long[numbersSize];
        Arrays.fill(numbers, Long.MAX_VALUE);
        for (int i = numbersSize / 2; i < numbersSize / 2 + numberCount; i++) {
            numbers[i] = Long.parseLong(bf.readLine());
        }
        settingTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minimumPrintCount; i++) {
            st = new StringTokenizer(bf.readLine());
            long start = Long.parseLong(st.nextToken()) + numbersSize / 2 - 1;
            long end = Long.parseLong(st.nextToken()) + numbersSize / 2 - 1;
            long minimum = getMinimum(start, end);
            sb.append(minimum).append('\n');
        }
        System.out.println(sb);

    }

    private static void settingTree() {
        int temp = numbers.length - 1;
        while (temp > 1) {
            if (numbers[temp / 2] > numbers[temp]) {
                numbers[temp / 2] = numbers[temp];
            }
            temp--;
        }
    }

    private static long getMinimum(long start, long end) {
        long minimum = Long.MAX_VALUE;

        while (start <= end) {
            if (start % 2 == 1) {
                minimum = Long.min(minimum, numbers[(int) start]);
                start++;
            }
            if (end % 2 == 0) {
                minimum = Long.min(minimum, numbers[(int) end]);
                end--;
            }
            start /= 2;
            end /= 2;
        }

        return minimum;
    }
}

