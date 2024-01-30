import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberSize = Integer.parseInt(bf.readLine());
        int[] numbers = new int[numberSize];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < numberSize; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int goodNumbers = 0;

        for (int i = numberSize - 1; i >= 0; i--) {
            int goodNumber = numbers[i];
            int start = 0, end = numberSize - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }
                if ((numbers[start] + numbers[end]) > goodNumber) {
                    end--;
                } else if ((numbers[start] + numbers[end]) < goodNumber) {
                    start++;
                } else {
                    goodNumbers++;
                    break;
                }
            }
        }
        System.out.println(goodNumbers);


    }


}