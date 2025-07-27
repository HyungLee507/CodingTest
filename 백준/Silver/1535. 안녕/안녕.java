
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] life = new int[N];
        int[] joyful = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            life[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            joyful[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100];

        for (int i = 0; i < N; i++) {
            for (int h = 99; h >= life[i]; h--) {
                dp[h] = Math.max(dp[h], dp[h - life[i]] + joyful[i]);
            }
        }

        int ans = 0;
        for (int h = 0; h < 100; h++) {
            ans = Math.max(ans, dp[h]);
        }

        System.out.println(ans);
    }
}
