import java.util.*;
class Solution {
    private static final int PUDDLE = -1; 
    private long[][] map; 
    public int solution(int m, int n, int[][] puddles) {
        init(m,n,puddles);
        map[1][2] = map[1][2] == PUDDLE ? 0 : 1;
        map[2][1] = map[2][1] == PUDDLE ? 0 : 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if ((i == 1 && j == 2) || (i == 2 && j == 1) || map[i][j] == PUDDLE) {
                    continue;
                }
                if (map[i - 1][j] != PUDDLE && map[i][j - 1] != PUDDLE) {
                    map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1_000_000_007;
                    continue;
                }
                if (map[i - 1][j] == PUDDLE && map[i][j - 1] != PUDDLE) {
                    map[i][j] = map[i][j - 1];
                    continue;
                }
                if (map[i - 1][j] != PUDDLE && map[i][j - 1] == PUDDLE) {
                    map[i][j] = map[i - 1][j];
                }
            }
        }
        return (int)map[m][n];
    }
    
    
    
    
    
    
    private void init(int m, int n,int[][] puddles){
        map = new long[m+1][n+1];
        for(int[] puddle : puddles){
            map[puddle[0]][puddle[1]] = PUDDLE;
        }
    }
}