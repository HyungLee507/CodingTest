import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numbers = Integer.parseInt(bf.readLine());
        int[][] dp = new int[numbers + 1][2];
        int[] number = new int[numbers + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < numbers; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        int max = number[0];
        dp[0][0] = number[0];
        dp[0][1] = number[0];

        for (int i = 1; i < numbers; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + number[i], number[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + number[i]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        System.out.println(max);
    }


}