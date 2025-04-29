
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String s;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        int n = s.length();
        dp = new int[n][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(solve(0, n - 1));
    }

    static int solve(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int res = 0;

        if ((s.charAt(i) == 'a' && s.charAt(j) == 't') ||
                (s.charAt(i) == 'g' && s.charAt(j) == 'c')) {
            res = Math.max(res, solve(i + 1, j - 1) + 2);
        }

        for (int k = i; k < j; k++) {
            res = Math.max(res, solve(i, k) + solve(k + 1, j));
        }

        dp[i][j] = res;
        return res;
    }
}
