import java.util.*;
class Solution {
    private static final int[] dirX = {1,-1,0,0};
    private static final int[] dirY = {0,0,1,-1};
    private static class Robot{
        int x ;
        int y ;
        int count;
        public Robot (int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
        
    }
    
    public int solution(int[][] maps) {
        int answer = -1;
        int row = maps.length;
        int col = maps[0].length;
        boolean[][] visited = new boolean[row][col];
        Queue<Robot> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new Robot(0,0,1));
        while(!queue.isEmpty()){
            Robot cur = queue.poll();
            if(cur.x == row -1 && cur.y == col-1){
                answer = cur.count;
                break;
            }
            for(int i=0; i<4; i++){
                int nextX = cur.x + dirX[i];
                int nextY = cur.y + dirY[i];
                if(nextX >=0 && nextX < row && nextY>=0 && nextY < col && !visited[nextX][nextY]&& maps[nextX][nextY] ==1){
                    visited[nextX][nextY] = true;
                    queue.add(new Robot(nextX,nextY,cur.count+1));
                }
            }
        }
        
        return answer;
    }
}