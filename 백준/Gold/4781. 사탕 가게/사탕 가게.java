import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_COST = 10001;
    static int N, M;
    static int[] calories;
    static int[] prices;
    static int[] dp = new int[MAX_COST];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            double money = Double.parseDouble(st.nextToken());
            if (N == 0 && money == 0.00) break;

            M = (int) Math.round(money * 100);
            calories = new int[N];
            prices = new int[N];
            dp = new int[M + 1]; // 최대 가격까지 초기화

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                calories[i] = Integer.parseInt(st.nextToken());
                prices[i] = (int) Math.round(Double.parseDouble(st.nextToken()) * 100);
            }

            // Bottom-Up DP
            for (int i = 0; i < N; i++) {
                int cal = calories[i];
                int price = prices[i];
                for (int j = price; j <= M; j++) {
                    dp[j] = Math.max(dp[j], dp[j - price] + cal);
                }
            }

            System.out.println(dp[M]);
        }
    }
}
