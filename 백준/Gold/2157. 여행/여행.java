
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] costs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (M < 2) {
            System.out.println(0);
            return;
        }
        costs = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(costs[i], -1);
        }
        dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -2);
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (start >= end) {
                continue;
            }
            if (costs[start][end] == -1 || costs[start][end] < cost) {
                costs[start][end] = cost;
            }
        }
        System.out.println(dfs(1, 0, 1));
    }

    private static int dfs(int idx, int nowCost, int hop) {
        if (idx == N) {
            return 0;
        }
        if (hop == M) {
            return -1;
        }

        if (dp[idx][hop] != -2) {
            return dp[idx][hop];
        }

        int res = -1;
        for (int i = idx + 1; i <= N; i++) {
            if (costs[idx][i] == -1) {
                continue;
            }
            int next = dfs(i, nowCost + costs[idx][i], hop + 1);
            if (next == -1) {
                continue;
            }
            int cand = costs[idx][i] + next;
            if (res == -1 || res < cand) {
                res = cand;
            }
        }

        return dp[idx][hop] = res;
    }
}
