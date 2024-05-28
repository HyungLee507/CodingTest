import java.util.*;
class Solution {
    private static final int[] dirX = {0, 0, 1, -1};
    private static final int[] dirY = {1, -1, 0, 0};
    int[][] map;
    boolean[][][] visited;
    int minCost = Integer.MAX_VALUE;
    private static class Location implements Comparable<Location> {
        int x;
        int y;
        int dirX;
        int dirY;
        int cost;
        public Location(int x, int y, int dirX, int dirY, int cost) {
            this.x = x;
            this.y = y;
            this.dirX = dirX;
            this.dirY = dirY;
            this.cost = cost;
        }

        @Override
        public int compareTo(Location o) {
            return Integer.compare(cost, o.cost);
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        map = new int[n][n];
        visited = new boolean[4][n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.copyOf(board[i], board[i].length);
        }
        bfs();
        return minCost;
    }
    private void bfs() {
        Queue<Location> queue = new PriorityQueue<>();

        if (map[1][0] == 0) {
            visited[0][0][1] = true;
            queue.add(new Location(1, 0, 1, 0, 100));
        }
        if (map[0][1] == 0) {
            visited[0][0][1] = true;
            queue.add(new Location(0, 1, 0, 1, 100));
        }
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            for (int i = 0; i < 4; i++) {
                if (now.dirX == dirX[i] && now.dirY == dirY[i]) {
                    visited[i][now.x][now.y] = true;
                    break;
                }
            }
            if (now.x == map.length - 1 && now.y == map.length - 1) {
                minCost = now.cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map.length && map[nextX][nextY] == 0) {
                    if (isCorner(now.dirX, now.dirY, dirX[i], dirY[i]) && !visited[i][nextX][nextY]) {
                        queue.add(new Location(nextX, nextY, dirX[i], dirY[i], now.cost + 600));
                    }
                    if (!isCorner(now.dirX, now.dirY, dirX[i], dirY[i]) && !visited[i][nextX][nextY]) {
                        queue.add(new Location(nextX, nextY, dirX[i], dirY[i], now.cost + 100));
                    }
                }
            }
        }
    }

    private boolean isCorner(int nowDirX, int nowDirY, int dirX, int dirY) {
        if (Math.abs(nowDirX) == Math.abs(dirX) && Math.abs(nowDirY) == Math.abs(dirY)) {
            return false;
        }
        return true;
    }
}