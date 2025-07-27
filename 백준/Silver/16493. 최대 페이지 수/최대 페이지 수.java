import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] dp;
    static int[][] reading;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        reading = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int chapter = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            reading[i][0] = chapter;
            reading[i][1] = day;
        }
        dp = new int[M + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(1, 0));
    }

    private static int dfs(int chapterIdx, int usedDays) {
        if (chapterIdx > M) {
            return 0;
        }

        if (dp[chapterIdx][usedDays] != -1) {
            return dp[chapterIdx][usedDays];
        }

        int res = 0;

        res = dfs(chapterIdx + 1, usedDays);

        int days = reading[chapterIdx][0];
        int pages = reading[chapterIdx][1];

        if (usedDays + days <= N) {
            res = Math.max(res, dfs(chapterIdx + 1, usedDays + days) + pages);
        }

        return dp[chapterIdx][usedDays] = res;
    }
}
