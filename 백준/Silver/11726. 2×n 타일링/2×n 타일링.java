import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int letters = Integer.parseInt(bf.readLine());

        long[] numbers = new long[letters + 1];
        numbers[1] = 1;
        if (letters >= 2) {
            numbers[2] = 2;
        }
        for (int i = 3; i < letters + 1; i++) {
            numbers[i] = (numbers[i - 1] + numbers[i - 2]) % 10007;
        }
        System.out.println(numbers[letters]);
    }
}