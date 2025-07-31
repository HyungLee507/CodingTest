
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] benefits, choice;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        benefits = new int[N + 1][M + 1]; // benefits[money][company] 돈에 따른 기업에 투자했을때 이득
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            int cost = Integer.parseInt(st.nextToken()); // 재낄라면 필요.
            for (int j = 1; j <= M; j++) {
                int benefit = Integer.parseInt(st.nextToken());
                benefits[i][j] = benefit;
            }
        }
        dp = new int[M + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -1);
        }
        choice = new int[M + 1][N + 1];

        int money = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            sb.append(choice[i][money]).append(" ");
            money += choice[i][money];
        }
        System.out.println(dfs(0, 0));
        trace(1, 0);
    }

    private static int dfs(int idx, int money) {
        if (money > N || idx > M) {
            return 0;
        }
        if (dp[idx][money] != -1) {
            return dp[idx][money];
        }
        int res = 0;
        for (int i = 0; i + money <= N; i++) {
            int profit = benefits[i][idx] + dfs(idx + 1, money + i);
            if (profit > res) {
                res = profit;
                choice[idx][money] = i;
            }
        }
        return dp[idx][money] = res;

    }

    private static void trace(int company, int money) {
        for (int i = company; i <= M; i++) {
            int invest = choice[i][money];
            System.out.print(invest + " ");
            money += invest;
        }
    }
}
