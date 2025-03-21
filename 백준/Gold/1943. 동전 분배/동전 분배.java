
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[][] coins;
    static int total, n;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 0; i < 3; i++) {
            n = Integer.parseInt(bf.readLine());
            coins = new int[n][2];
            total = 0;
            result = false;
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(bf.readLine());
                // 값을 저장하고
                int value = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                total += (value * cnt);
                coins[j][0] = value;
                coins[j][1] = cnt;
            }
            if (total % 2 != 0) {
                System.out.println(0);
                continue;
            }
            dp = new int[n][50001];
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[j], -1);
            }
            System.out.println(dfs(0, 0));
            // 여기서 dp 관련 로직 수행

        }
        bf.close();

    }

    private static int dfs(int depth, int value) {
        if (value == (total / 2)) {
            return 1;
        }
        if (depth >= n || value > total / 2) {
            return 0;
        }
        if (dp[depth][value] != -1) {
            return dp[depth][value];
        }
        int res = -1;
        for (int i = 0; i <= coins[depth][1]; i++) {
            res = Math.max(dfs(depth + 1, value + i * coins[depth][0]), res);
        }
        return dp[depth][value] = res;
    }
}
