import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, T;
    static int[][] dp, table;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        table = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 1][T + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int chapter, int totalTime) {
        if (chapter > N) {
            return 0;
        }
        if (dp[chapter][totalTime] != -1) {
            return dp[chapter][totalTime];
        }
        int res = dfs(chapter + 1, totalTime);

        int time = table[chapter][0];
        int score = table[chapter][1];

        if (totalTime + time <= T) {
            res = Math.max(res, dfs(chapter + 1, totalTime + time) + score);
        }
        return dp[chapter][totalTime] = res;
    }


}
