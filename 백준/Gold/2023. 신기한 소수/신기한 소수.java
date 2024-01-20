import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static final int[] primeNumbersLengthOne = new int[]{2, 3, 5, 7};
    static int numberLength;
    static List<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        numberLength = Integer.parseInt(bf.readLine());
        for (int i : primeNumbersLengthOne) {
            dfs(i, 1);
        }
        primeNumbers.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);


    }

    static void dfs(int number, int depth) {
        if (depth == numberLength) {
            primeNumbers.add(number);
        }
        for (int i = 1; i < 10; i++) {
            if (isPrime(number * 10 + i)) {
                dfs(number * 10 + i, depth + 1);
            }
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


}