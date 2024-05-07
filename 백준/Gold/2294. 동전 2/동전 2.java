import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k + 1];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(arr);
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
            }
        }
        if (dp[k] != Integer.MAX_VALUE - 1) {
            System.out.println(dp[k]);
        } else {
            System.out.println(-1);
        }

    }
}