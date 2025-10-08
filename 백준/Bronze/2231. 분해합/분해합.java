import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static int digitSum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int digits = String.valueOf(N).length();
        int start = Math.max(1, N - 9 * digits);

        for (int m = start; m < N; m++) {
            if (m + digitSum(m) == N) {
                System.out.println(m);
                return;
            }
        }
        System.out.println(0);
    }
}
