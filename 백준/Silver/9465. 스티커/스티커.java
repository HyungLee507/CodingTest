
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int cnt = Integer.parseInt(bf.readLine());
        for (int i = 0; i < cnt; i++) {
            int n = Integer.parseInt(bf.readLine());
            int[][] sticker = new int[2][n];
            int[][] dp = new int[2][n];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < n; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            if (n != 1) {
                //TODO : DP 반복문 진행
                // [][1] 은 init 해줘야 됨.
                dp[0][1] = dp[1][0] + sticker[0][1];
                dp[1][1] = dp[0][0] + sticker[1][1];
                // [][2] 에서부터는 3개를 체크해줘야 됨.
                for (int k = 2; k < n; k++) {
                    dp[0][k] = sticker[0][k] + Math.max(dp[0][k - 2], Math.max(dp[1][k - 1], dp[1][k - 2]));
                    dp[1][k] = sticker[1][k] + Math.max(dp[1][k - 2], Math.max(dp[0][k - 1], dp[0][k - 2]));
                }
            }
            System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
        }
    }
}
