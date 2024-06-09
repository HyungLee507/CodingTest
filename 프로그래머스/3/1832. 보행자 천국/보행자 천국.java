import java.util.*;
class Solution {
    int MOD = 20170805;
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m][n];
        dp[VERTICAL][0][0] = 0;
        dp[HORIZONTAL][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //자유롭게 통과 가능
                if (cityMap[i][j] == 0) {
                    // 수직 방향 더해줌.
                    if (i + 1 < m) {
                        dp[VERTICAL][i + 1][j] =
                                (dp[VERTICAL][i + 1][j] + (dp[VERTICAL][i][j] + dp[HORIZONTAL][i][j])) % MOD;
                    }
                    if (j + 1 < n) {
                        dp[HORIZONTAL][i][j + 1] =
                                (dp[HORIZONTAL][i][j + 1] + (dp[VERTICAL][i][j] + dp[HORIZONTAL][i][j])) % MOD;
                    }
                    continue;
                }
                // 직선 방향만 가능.
                if (cityMap[i][j] == 2) {
                    if (i + 1 < m) {
                        dp[VERTICAL][i + 1][j] = (dp[VERTICAL][i + 1][j] + dp[VERTICAL][i][j]) % MOD;
                    }
                    if (j + 1 < n) {
                        dp[HORIZONTAL][i][j + 1] = (dp[HORIZONTAL][i][j + 1] + dp[HORIZONTAL][i][j]) % MOD;
                    }
                }
            }
        }
        return (dp[VERTICAL][m - 1][n - 1] + dp[HORIZONTAL][m - 1][n - 1]) % MOD;
    }
}