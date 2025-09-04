
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] orders;
    static int[][][] dp;
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        orders = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            orders[i][0] = Integer.parseInt(st.nextToken());
            orders[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][M + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int m = 0; m <= M; m++) {
                Arrays.fill(dp[i][m], -1);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    private static int dfs(int depth, int cheeze, int potato) {
        if (cheeze > M || potato > K) {
            return -1;
        }
        if (depth == N) {
            return 0;
        }

        if (dp[depth][cheeze][potato] != -1) {
            return dp[depth][cheeze][potato];
        }

        int res = dfs(depth + 1, cheeze, potato);

        int a = orders[depth][0], b = orders[depth][1];
        if (cheeze + a <= M && potato + b <= K) {
            res = Math.max(res, dfs(depth + 1, cheeze + a, potato + b) + 1);
        }

        return dp[depth][cheeze][potato] = res;
    }
}
