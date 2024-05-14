import java.util.*;
class Solution {
    public static final int ROW = 0;
    public static final int COL = 1;

    static boolean[][][] visited;
    static int[][] map;
    static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 가로 - 세로 회전 
    public static final int[][] rRotate = {{-1, 0}, {0, 0}, {-1, 1}, {0, 1}};
    public static final int[][] rCheck = {{-1, 1}, {1, 1}, {-1, 0}, {1, 0}};
    // 세로 - 가로 회전
    public static final int[][] cRotate = {{0, -1}, {1, -1}, {0, 0}, {1, 0}};
    public static final int[][] cCheck = {{1, -1}, {0, -1}, {1, 1}, {0, 1}};

    private static class Robot {
        int x;
        int y;
        int dir;
        int count;

        public Robot(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length - 1;
        visited = new boolean[2][board.length][board.length];
        map = board;
        for (int i = 0; i < map.length; i++) {
            board[i] = Arrays.copyOf(board[i], board[i].length);
        }
        Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(0, 0, ROW, 0));
        visited[ROW][0][0] = true;
        while (!queue.isEmpty()) {
            Robot now = queue.poll();
            if ((now.dir == ROW && now.x == n && now.y + 1 == n) ||
                    (now.dir == COL && now.x + 1 == n && now.y == n)) {
                answer = now.count;
                break;
            }
            // 단순 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + move[i][0];
                int nextY = now.y + move[i][1];
                if (check(nextX, nextY, now.dir)) {
                    visited[now.dir][nextX][nextY] = true;
                    queue.offer(new Robot(nextX, nextY, now.dir, now.count + 1));
                }
            }
            // 회전에 따른
            if (now.dir == ROW) {
                for (int i = 0; i < 4; i++) {
                    int nextX = now.x + rRotate[i][0];
                    int nextY = now.y + rRotate[i][1];
                    int nextDir = COL;

                    if (check(nextX, nextY, nextDir) && map[now.x + rCheck[i][0]][now.y + rCheck[i][1]] == 0) {
                        visited[nextDir][nextX][nextY] = true;
                        queue.offer(new Robot(nextX, nextY, nextDir, now.count + 1));
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int nextX = now.x + cRotate[i][0];
                    int nextY = now.y + cRotate[i][1];
                    int nextDir = ROW;
                    if (check(nextX, nextY, nextDir) && map[now.x + cCheck[i][0]][now.y + cCheck[i][1]] == 0) {
                        visited[nextDir][nextX][nextY] = true;
                        queue.offer(new Robot(nextX, nextY, nextDir, now.count + 1));
                    }
                }
            }
        }
        return answer;
    }
    public boolean check(int x, int y, int dir) {
        if (x < 0 || x >= map.length || y < 0 || y >= map.length || visited[dir][x][y] || map[x][y] == 1) {
            return false;
        }
        if (dir == ROW && (y + 1 >= map.length || map[x][y + 1] == 1)) {
            return false;
        }
        if (dir == COL && (x + 1 >= map.length || map[x + 1][y] == 1)) {
            return false;
        }
        return true;
    }
}