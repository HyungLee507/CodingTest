
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][][] dp;
    static int N;
    public static final int MOD = 1_000_000_000;
    public static final int FULL = (1 << 10) - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        if (N < 10) {
            System.out.println(0);
            return;
        }
        if (N == 10) {
            System.out.println(1);
            return;
        }
        dp = new int[N + 1][10][1 << 10];
        for (int i = 0; i <= N; i++) {
            for (int d = 0; d < 10; d++) {
                Arrays.fill(dp[i][d], -1);
            }
        }

        long ans = 0;
        for (int i = 1; i <= 9; i++) {
            int mask = (1 << i);
            ans += dfs(1, i, mask);
            ans %= MOD;
        }
        System.out.println(ans % MOD);
    }

    static int dfs(int now, int last, int mask) {
        if (now == N) {
            return (mask == FULL) ? 1 : 0;
        }
        int ret = dp[now][last][mask];
        if (ret != -1) {
            return ret;
        }

        long sum = 0;

        if (last > 0) {
            int nxt = last - 1;
            sum += dfs(now + 1, nxt, mask | (1 << nxt));
        }
        if (last < 9) {
            int nxt = last + 1;
            sum += dfs(now + 1, nxt, mask | (1 << nxt));
        }

        ret = (int) (sum % MOD);
        dp[now][last][mask] = ret;
        return ret;
    }
}
