
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dp;
    static int[][] infos;
    static int N, K, x;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        infos = new int[N][2];
        dp = new int[N + 1][K + 1][K * x + 1];
        for (int i = 0; i <= N; i++) {
            for (int t = 0; t <= K; t++) {
                Arrays.fill(dp[i][t], -1);
            }
        }

        // dp init 필요
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            infos[i][0] = a;
            infos[i][1] = b;
        }
        System.out.println(dfs(0, 0, 0));
    }

    private static int dfs(int i, int t, int s) {
        if (t > K || s > K * x) {
            return 0;
        }
        if (t == K) {
            return s * (K * x - s);
        }
        if (i == N) {
            return 0;
        }
        
        if (dp[i][t][s] != -1) {
            return dp[i][t][s];
        }

        int best = -1;
        // 안 뽑기
        best = Math.max(best, dfs(i + 1, t, s));
        // 뽑기
        best = Math.max(best, dfs(i + 1, t + 1, s + infos[i][0]));

        return dp[i][t][s] = best;
    }


}
