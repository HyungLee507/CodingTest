import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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
//            if (temp > 0) {
//                temp = temp / 2;
//                numbersSize *= 2;
//            }
            temp = temp / 2;
            numbersSize *= 2;
        }
        numbers = new long[numbersSize];

        for (int i = 0; i < numberCount; i++) {
            long number = Long.parseLong(bf.readLine());
            numbers[numbersSize / 2 + i] = number;
        }

        settingTree();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < intervalSumCount + changeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            long value1 = Long.parseLong(st.nextToken());
            long value2 = Long.parseLong(st.nextToken());

            if (command == 1) {
                // 세그먼트 트리 구간 합 세팅
//                numbers[(int) (numbersSize / 2 + value1 - 1)] = value2;
                changeValue(value1, value2);
            }
            if (command == 2) {
                // 세그먼트 트리를 활용한 구간 합 출력
                sb.append(getSum(value1, value2)).append('\n');
            }
        }
        System.out.println(sb);


    }

    private static long getSum(long start, long end) {

        long sum = 0;
        start = numbers.length / 2 - 1 + start;
        end = numbers.length / 2 - 1 + end;
        while (start <= end) {
            if (start % 2 == 1) {
                sum += numbers[(int) start];
                start++;
            }
            if (end % 2 == 0) {
                sum += numbers[(int) end];
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
                numbers[i] = numbers[i * 2] + numbers[i * 2 + 1];
            }
            temp /= 2;
        }
    }

    private static void changeValue(long index, long value) {

        index = numbers.length / 2 + index - 1;
        long diff = numbers[(int) index] - value;
        numbers[(int) index] = value;
        int temp = (int) (index / 2);
        while (temp >= 1) {
            numbers[temp] -= diff;
            temp /= 2;
        }
    }
}