import java.util.*;
class Solution {
    
    private int[][] map ;
    private boolean[][] visited;
    private static final int[] dirX = {0,0,1,-1};
    private static final int[] dirY = {1,-1,0,0};
    int startX;
    int startY;
    private static class Location{
        int x; 
        int y;
        int cost;
        public Location(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }    
    public int solution(String[] board) {
        init(board);
        int min = bfs();
        if(min == Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }
    private int bfs() {
        int min = Integer.MAX_VALUE;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startX, startY, 0));
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            for (int i = 0; i < 4; i++) {
                //todo : 방향에 따른 이동(반복문으로 하겠네 ) -> visited 처리 -> G 이면 Min 값 최신화 또는 위치 이동
                int nextX = now.x;
                int nextY = now.y;
                while (true) {
                    if (isEncounterWall(nextX + dirX[i], nextY + dirY[i])) {
                        if (!visited[nextX][nextY]) {
                            queue.add(new Location(nextX, nextY, now.cost + 1));
                            visited[nextX][nextY] = true;
                        }
                        break;
                    }

                    nextX += dirX[i];
                    nextY += dirY[i];
                }
                if (isEncounterGoal(nextX, nextY)) {
                    min = Integer.min(now.cost + 1, min);
                    break;
                }
            }
        }
        return min;
    }
    private boolean isEncounterWall(int x, int y) {
        if ((x < 0 || x >= map.length || y < 0 || y >= map[0].length)) {
            return true;
        }
        if (map[x][y] == -1) {
            return true;
        }
        return false;
    }
    private boolean isEncounterGoal(int x, int y) {
        if (map[x][y] == 2) {
            return true;
        }
        return false;
    }
    
    private void init(String[] board){
        int row = board.length;
        int col = board[0].length();
        map = new int[row][col];
        visited = new boolean[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(board[i].charAt(j)=='D'){
                    map[i][j] = -1;
                }
                if(board[i].charAt(j)=='G'){
                    map[i][j] = 2;
                }
                if(board[i].charAt(j)=='R'){
                    map[i][j] = 1;
                    startX = i;
                    startY = j;
                }
            }
        }
        
        
    }
}