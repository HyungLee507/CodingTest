import java.util.*;
class Solution {
    int[][] map;
    boolean[][] visited;
    static final int[] dirX = {1, -1, 0, 0};
    static final int[] dirY = {0, 0, 1, -1};
    static final int[] dir2X = {1, 1, 1, -1, -1, -1, 0, 0};
    static final int[] dir2Y = {1, 0, -1, 1, 0, -1, 1, -1};
    
    private static class Block{
        int x; 
        int y;
        int depth;
        
        public Block(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
        
    }
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        init(rectangle);
        makeBorderLine();
        return getShortRoute(characterX, characterY, itemX, itemY);
    }
    
    private int getShortRoute(int startX, int startY, int endX, int endY) {
        int minDepth = Integer.MAX_VALUE;
        visited[startX * 2][startY * 2] = true;
        Queue<Block> queue = new LinkedList<>();
        queue.add(new Block(startX * 2, startY * 2, 0));
        while (!queue.isEmpty()) {
            Block now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            if (nowX == endX * 2 && nowY == endY * 2) {
                minDepth = Math.min(minDepth, now.depth);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = dirX[i] + nowX;
                int nextY = dirY[i] + nowY;
                if (nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map.length && map[nextX][nextY] == 1
                        && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.add(new Block(nextX, nextY, now.depth + 1));
                }
            }
        }
        return minDepth / 2;
    }
    
    
    private void makeBorderLine() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (map[x][y] != 0 && isNotBorder(x, y)) {
                    map[x][y] = 2;
                }
            }
        }
    }
    
    private boolean isNotBorder(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nextX = dir2X[i] + x;
            int nextY = dir2Y[i] + y;
            if (nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map.length && map[nextX][nextY] == 0) {
                return false;
            }
        }
        return true;
    }
    
    private void init(int[][] rectangle) {
        int maxLength = 0;
        for (int[] temp : rectangle) {
            maxLength = Math.max(Arrays.stream(temp).max().orElse(-1), maxLength);
        }
        maxLength *= 2;
        map = new int[maxLength + 2][maxLength + 2];
        visited = new boolean[maxLength + 2][maxLength + 2];
        for (int[] temp : rectangle) {
            int x1 = temp[0];
            int y1 = temp[1];
            int x2 = temp[2];
            int y2 = temp[3];
            for (int i = x1 * 2; i < x2 * 2 + 1; i++) {
                for (int j = y1 * 2; j < y2 * 2 + 1; j++) {
                    map[i][j] = 1;
                }
            }
        }
    }
}