import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(bf.readLine());
        int[][] price = new int[n + 1][4];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= 3; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long[][] dp = new long[n + 1][4];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                dp[i][j] = getMin(i, j, dp) + price[i][j];
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = 1; i < dp[0].length; i++) {
            min = Math.min(min, dp[n][i]);
        }
        System.out.println(min);
    }

    private static long getMin(int row, int index, long[][] dp) {
        long min = Long.MAX_VALUE;
        for (int i = 1; i < dp[0].length; i++) {
            if (i != index) {
                min = Math.min(dp[row - 1][i], min);
            }
        }
        return min;
    }
}