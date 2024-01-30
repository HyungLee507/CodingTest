import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long start = Long.parseLong(st.nextToken());
        long end = Long.parseLong(st.nextToken());

        boolean[] primes = sieveOfEratosthenes();
        int halfPrimes = 0;
        for (long i = 1; i <= Math.sqrt(end); i++) {
            if (!primes[(int) i]) {
                for (long j = i * i; j <= end; j = j * i) {

                    if (j >= start) {
                        halfPrimes++;
                    }
                    if (BigInteger.valueOf(j).multiply(BigInteger.valueOf(i)).compareTo(
                            BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
                        break;
                    }
                }
            }
        }
        System.out.println(halfPrimes);

    }


    private static boolean[] sieveOfEratosthenes() {

        boolean[] primes = new boolean[10000001];
        primes[0] = false;
        primes[1] = true;

        for (int i = 2; i <= 10000000; i++) {
            if (primes[i]) {
                continue;
            }
            for (int j = i + i; j <= 10000000; j = j + i) {
                primes[j] = true;
            }
        }
        return primes;
    }

}