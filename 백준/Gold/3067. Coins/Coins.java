
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int iterCnt = Integer.parseInt(bf.readLine());

        for (int t = 0; t < iterCnt; t++) {
            int N = Integer.parseInt(bf.readLine());
            int[] coins = new int[N];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int total = Integer.parseInt(bf.readLine());
            int[] dp = new int[total + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int i = coin; i <= total; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            sb.append(dp[total]).append('\n');
        }

        System.out.print(sb);
    }
}
