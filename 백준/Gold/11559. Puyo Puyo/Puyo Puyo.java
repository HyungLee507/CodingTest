import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class Main {
    private static final int[] dirX = {0, 0, 1, -1};
    private static final int[] dirY = {1, -1, 0, 0};
    public static final int ROW = 12;
    public static final int COL = 6;
    static int count = 0;
    static int[][] map = new int[ROW][COL];

    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < ROW; i++) {
            String temp = bf.readLine();
            for (int j = 0; j < COL; j++) {
                map[i][j] = convertPuyo(temp.charAt(j));
            }
        }
        while (canExplode()) {
            // explode(); -> 여기서 카운트 증가
            count++;
            treatMap();
        }
        System.out.println(count);
    }

    private static boolean canExplode() {
        boolean result = false;
        for (int i = ROW - 1; i >= 0; i--) {
            for (int j = 0; j < COL; j++) {
                if (map[i][j] != 0 && bfs(i, j, map[i][j])) {
                    result = true;
                }
            }
        }
        return result;
    }

    private static int convertPuyo(char ch) {
        if (ch == 'R') {
            return 1;
        }
        if (ch == 'G') {
            return 2;
        }
        if (ch == 'B') {
            return 3;
        }
        if (ch == 'P') {
            return 4;
        }
        if (ch == 'Y') {
            return 5;
        } else {
            return 0;
        }
    }

    private static void treatMap() {
        for (int i = 0; i < COL; i++) {
            Queue<Integer> queue = new LinkedList<>();
            for (int j = ROW - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    queue.add(map[j][i]);
                    map[j][i] = 0;
                }
            }
            int size = queue.size();
            int temp = 11;
            while (!queue.isEmpty()) {
                map[temp][i] = queue.poll();
                temp--;
            }
        }
    }

    private static boolean bfs(int x, int y, int puyo) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> targets = new ArrayList<>();
        boolean[][] visited = new boolean[ROW][COL];
        queue.add(new int[]{x, y});
        targets.add(new int[]{x, y});
        visited[x][y] = true;
        int total = 1;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dirX[i];
                int nextY = now[1] + dirY[i];
                if (nextX >= 0 && nextY >= 0 && nextX < ROW && nextY < COL && !visited[nextX][nextY]
                        && map[nextX][nextY] == puyo) {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    targets.add(new int[]{nextX, nextY});
                    total++;
                }
            }
        }
        if (total >= 4) {
            for (int i = 0; i < targets.size(); i++) {
                int[] target = targets.get(i);
                map[target[0]][target[1]] = 0;
            }
//            count++;
            return true;
        } else {
            return false;
        }
    }
}