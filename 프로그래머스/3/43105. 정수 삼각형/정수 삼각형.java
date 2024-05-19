import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n+1][n+1];
        for(int i=1; i<=n;i++){
            for(int j=1; j<=i; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i-1][j-1];
            }
        }
        int answer = Arrays.stream(dp[n]).max().orElse(0);     
        return answer;
    }
}