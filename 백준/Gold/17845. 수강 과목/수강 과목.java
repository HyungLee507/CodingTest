
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] dp, table;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1][N + 1];
        table = new int[K + 1][2];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(bf.readLine());
            int value = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            table[i][0] = value;
            table[i][1] = time;
        }
        System.out.println(dfs(1, 0));
    }

    private static int dfs(int tableIdx, int usedDay) {
        if (usedDay > N || tableIdx > K) {
            return 0;
        }
        if (dp[tableIdx][usedDay] != -1) {
            return dp[tableIdx][usedDay];
        }

        int res = dfs(tableIdx + 1, usedDay);

        int value = table[tableIdx][0];
        int time = table[tableIdx][1];

        if (usedDay + time <= N) {
            res = Math.max(res, dfs(tableIdx + 1, usedDay + time) + value);
        }
        return dp[tableIdx][usedDay] = res;
    }
}
