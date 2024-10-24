

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[i] = arr[i];
            } else if (i == 2) {
                dp[i] = arr[i - 1] + arr[i];
            } else {
                dp[i] = Integer.max(dp[i - 1], Integer.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
            }
        }
        System.out.println(dp[N]);
    }

}
