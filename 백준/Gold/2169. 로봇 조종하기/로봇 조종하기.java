import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][][] dp = new int[N][M][3];
        int NEG_INF = -1000000000; 
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                Arrays.fill(dp[i][j], NEG_INF);
            }
        }
        
        dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = map[0][0];
        for (int j = 1; j < M; j++){
            dp[0][j][0] = dp[0][j-1][0] + map[0][j];
        }
        
        for (int i = 1; i < N; i++){
            int[] vertical = new int[M];
            for (int j = 0; j < M; j++){
                vertical[j] = Math.max(
                    Math.max(dp[i-1][j][0], dp[i-1][j][1]),
                    dp[i-1][j][2]
                ) + map[i][j];
            }
            
            for (int j = 0; j < M; j++){
                dp[i][j][2] = vertical[j];
            }
            
            dp[i][0][0] = vertical[0]; 
            for (int j = 1; j < M; j++){
                dp[i][j][0] = Math.max(vertical[j], dp[i][j-1][0] + map[i][j]);
            }
            
            dp[i][M-1][1] = vertical[M-1]; 
            for (int j = M - 2; j >= 0; j--){
                dp[i][j][1] = Math.max(vertical[j], dp[i][j+1][1] + map[i][j]);
            }
        }
        
        int answer = Math.max(dp[N-1][M-1][0], dp[N-1][M-1][1]);
        System.out.println(answer);
        br.close();
    }
}
