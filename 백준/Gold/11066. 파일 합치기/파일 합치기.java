
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[] arr, sum;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(bf.readLine());
            arr = new int[n + 1];
            sum = new int[n + 1];
            dp = new int[n + 1][n + 1];

            st = new StringTokenizer(bf.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + arr[i];
                dp[i][i] = 0;
            }

            // 구간 길이 2 이상
            for (int len = 2; len <= n; len++) {
                for (int i = 1; i + len - 1 <= n; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
                    }
                }
            }
            sb.append(dp[1][n]).append('\n');
        }

        System.out.println(sb);
    }
}
