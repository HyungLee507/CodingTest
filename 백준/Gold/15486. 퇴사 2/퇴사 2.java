
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(bf.readLine());
        int[] dp = new int[n + 2];
        int[][] schedule = new int[2][n + 2];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            schedule[0][i] = Integer.parseInt(st.nextToken());
            schedule[1][i] = Integer.parseInt(st.nextToken());
        }
        int max = -1;
        for (int i = 1; i <= n + 1; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
            int next = i + schedule[0][i];
            int cost = schedule[1][i];
            if (next < n + 2) {
                dp[next] = Math.max(dp[next], max + cost);
            }
        }
        System.out.print(dp[n + 1]);
        bf.close();
    }
}
