
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[][] dp = new int[11][11];
        dp[0][0] = 1;

        for (int s = 0; s <= 10; s++) {
            for (int t = 0; t <= 10; t++) {
                if (dp[s][t] == 0) {
                    continue;
                }
                for (int k = 1; k <= 3; k++) {
                    int ns = s + k, nt = t + 1;
                    if (ns <= 10 && nt <= 10) {
                        dp[ns][nt] += dp[s][t];
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(bf.readLine());
            System.out.println(Arrays.stream(dp[temp]).sum());
        }
    }
}
