import java.util.*;
class Solution {
    private static class Location{
        int x;
        int y;
        int count;
        public Location(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    String[] str = {"d", "l", "r", "u"};
    int K;
    int row;
    int col;
    int[] end = new int[2];
    StringBuilder sb = new StringBuilder();
    String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        K = k;
        row = n;
        col = m;
        end[0] = r;
        end[1] = c;
        int count = (int) Math.abs(x- r) + (int) Math.abs(y-c);
        if(count > k || (k - count) % 2 !=0){
            return "impossible";
        }
        dfs(x,y,0);
        return answer;
        
    }
    private void dfs(int x, int y, int count) {
        if(answer != null){
            return;
        }
        if(count + distance(x, y, end[0], end[1]) > K) return;
        
        if (count == K) {
            if (x == end[0] && y == end[1] ) {
                answer = sb.toString();
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;
            if (nextX >= 1 && nextX <= row && nextY >= 1 && nextY <= col) {
                sb.append(str[i]);
                dfs(nextX, nextY, count + 1);
                sb.delete(count, count + 1);
            }
        }

    }
    private int distance(int x, int y, int r, int c){
        return (int)Math.abs(x-r) + (int)Math.abs(y-c);
    }
}