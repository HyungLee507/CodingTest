import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] dp;
    static int[] weights;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        weights = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][40001];
        dp[0][0] = true;

        for (int i = 0; i < N; i++) {
            for (int w = 0; w <= 40000; w++) {
                if (dp[i][w]) {
                    dp[i + 1][w] = true;
                    if (w + weights[i] <= 40000) {
                        dp[i + 1][w + weights[i]] = true;
                    }

                    int diff = Math.abs(w - weights[i]);
                    if (diff <= 40000) {
                        dp[i + 1][diff] = true;
                    }
                }
            }
        }

        int T = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int x = Integer.parseInt(st.nextToken());
            sb.append(dp[N][x] ? "Y " : "N ");
        }

        System.out.println(sb);
    }

}
