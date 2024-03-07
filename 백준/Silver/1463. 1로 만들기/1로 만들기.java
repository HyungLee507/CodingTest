import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bf.readLine());
        int[] numbers = initNumbers(number);
        System.out.println(numbers[number]);
    }

    private static int[] initNumbers(int number) {
        int[] numbers = new int[number + 1];
        numbers[1] = 0;
        if (number == 2) {
            numbers[2] = 1;
        }
        if (number >= 3) {
            numbers[2] = 1;
            numbers[3] = 1;
        }

        for (int i = 2; i < numbers.length; i++) {
            numbers[i] = numbers[i - 1] + 1;
            if (i % 2 == 0) {
                numbers[i] = Math.min(numbers[i], numbers[i / 2] + 1);
            }
            if (i % 3 == 0) {
                numbers[i] = Math.min(numbers[i], numbers[i / 3] + 1);
            }
        }
        return numbers;
    }

}