import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n+1][n+1];
        for(int i=0; i<=n; i++){
            Arrays.fill(map[i],20000001);
            map[i][i] = 0;
        }
        
        for(int i=0; i<fares.length; i++){
            map[fares[i][0]][fares[i][1]] = fares[i][2];
            map[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                }
            }
        }
        
        int minPrice = map[s][a] + map[s][b];
        for(int i=1; i<=n;i++){
            minPrice = Math.min(minPrice, map[s][i] + map[i][a] + map[i][b]);
        }
        return minPrice;
    }
}