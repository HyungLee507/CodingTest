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
        int[] value = new int[n + 1];
        Arrays.sort(value);
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(bf.readLine());
            for (int j = value[i]; j <= k; j++) {
                dp[j] += dp[j - value[i]];
            }
        }
        System.out.println(dp[k]);


    }
}