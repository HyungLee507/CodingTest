import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[][] infos;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N][K + 1];
        infos = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 4; j++) {
                infos[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1_000_000_000);
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int idx, int timeUsed) {
        if (idx == N) {
            return 0;
        }
        if (dp[idx][timeUsed] != -1_000_000_000) {
            return dp[idx][timeUsed];
        }

        int best = -1_000_000_000;

        // 1) 도보 선택
        int wTime = infos[idx][0], wMoney = infos[idx][1];
        if (timeUsed + wTime <= K) {
            best = Math.max(best, wMoney + dfs(idx + 1, timeUsed + wTime));
        }

        // 2) 자전거 선택
        int bTime = infos[idx][2], bMoney = infos[idx][3];
        if (timeUsed + bTime <= K) {
            best = Math.max(best, bMoney + dfs(idx + 1, timeUsed + bTime));
        }

        return dp[idx][timeUsed] = best;
    }
}
