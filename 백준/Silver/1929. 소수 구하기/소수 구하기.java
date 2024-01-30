import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] primes = sieveOfEratosthenes(end);
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (!primes[i]) {
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);

    }

    private static boolean[] sieveOfEratosthenes(int end) {

        boolean[] primes = new boolean[end + 1];
        primes[0] = false;
        primes[1] = true;

        for (int i = 2; i <= end; i++) {
            if (primes[i]) {
                continue;
            }
            for (int j = i + i; j <= end; j = j + i) {
                primes[j] = true;
            }
        }
        return primes;
    }

}