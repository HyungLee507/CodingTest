import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());

        boolean[] primes = sieveOfEratosthenes();
        for (int i = start; i <= Integer.MAX_VALUE; i++) {
            if (isPalindrome(i) && !primes[i]) {
                System.out.println(i);
                break;
            }
        }

    }

    private static boolean isPalindrome(int prime) {

        String numStr = String.valueOf(prime);
        int len = numStr.length();
        for (int i = 0; i < len / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean[] sieveOfEratosthenes() {

        boolean[] primes = new boolean[2000001];
        primes[0] = false;
        primes[1] = true;

        for (int i = 2; i <= 1000000; i++) {
            if (primes[i]) {
                continue;
            }
            for (int j = i + i; j <= 2000000; j = j + i) {
                primes[j] = true;
            }
        }
        return primes;
    }

}