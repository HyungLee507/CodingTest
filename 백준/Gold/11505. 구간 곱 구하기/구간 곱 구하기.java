import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final long MOD = 1_000_000_007;
    static long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int numberCount = Integer.parseInt(st.nextToken());
        int changeCount = Integer.parseInt(st.nextToken());
        int intervalSumCount = Integer.parseInt(st.nextToken());
        int temp = numberCount;
        int numbersSize = 2;
        while (temp != 0) {
            temp = temp / 2;
            numbersSize *= 2;
        }
        numbers = new long[numbersSize];
        Arrays.fill(numbers,1);

        for (int i = 0; i < numberCount; i++) {
            long number = Long.parseLong(bf.readLine());
            numbers[numbersSize / 2 + i] = number;
        }

        settingTree();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < intervalSumCount + changeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            int value1 = Integer.parseInt(st.nextToken());
            int value2 = Integer.parseInt(st.nextToken());

            if (command == 1) {
                changeValue(value1, value2);
            }
            if (command == 2) {
                sb.append(getMultiply(value1, value2)).append('\n');
            }
        }
        System.out.println(sb);
    }
    private static long getMultiply(int start, int end) {

        long sum = 1;
        start = numbers.length / 2 - 1 + start;
        end = numbers.length / 2 - 1 + end;
        while (start <= end) {
            if (start % 2 == 1) {
                sum = sum * numbers[start] % MOD;
                start++;
            }
            if (end % 2 == 0) {
                sum = sum * numbers[end] % MOD;
                end--;
            }
            start = (start) / 2;
            end = (end) / 2;
        }
        return sum;

    }

    private static void settingTree() {

        int temp = numbers.length / 2;
        while (temp > 1) {
            for (int i = temp / 2; i < temp; i++) {
                numbers[i] = numbers[i * 2] * numbers[i * 2 + 1] % MOD ;
            }
            temp /= 2;
        }
    }
    private static void changeValue(int index, long value) {
        index =   numbers.length / 2 - 1 + index;
        numbers[index] = value;
        while (index >= 1) {
            index /= 2;
            numbers[index] = numbers[index * 2] % MOD * numbers[index *2 +1]  % MOD;
        }
    }
}