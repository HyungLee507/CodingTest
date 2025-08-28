
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        int[] sum = new int[10000];
        for (int i = 1; i < 10000; i++) {
            sum[i] = sum[i - 1] + i;
        }
        int[] dp = new int[10000];
        for (int i = 1; i < 10000; i++) {
            dp[i] = dp[i - 1] + sum[i];
        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] coins = new int[10000];
        int m = 0;
        for (int i = 1; i < 10000; i++) {
            if (dp[i] > N) {
                break;
            }
            coins[m++] = dp[i];
        }

        int INF = 1_000_000_000;
        int[] minCnt = new int[N + 1];
        Arrays.fill(minCnt, INF);
        minCnt[0] = 0;

        for (int i = 0; i < m; i++) {
            int c = coins[i];
            for (int x = c; x <= N; x++) {
                if (minCnt[x - c] + 1 < minCnt[x]) {
                    minCnt[x] = minCnt[x - c] + 1;
                }
            }
        }
        System.out.println(minCnt[N]);
    }
}
