import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public static final int BLACK = 1;
    public static final int WHITE = -1;

    public static final int[] dirX = {1, 1, 1, -1, -1, -1, 0, 0};
    public static final int[] dirY = {1, -1, 0, 1, -1, 0, 1, -1};

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            map = new int[n + 1][n + 1];
            map[n / 2][n / 2] = WHITE;
            map[n / 2 + 1][n / 2 + 1] = WHITE;
            map[n / 2][n / 2 + 1] = BLACK;
            map[n / 2 + 1][n / 2] = BLACK;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                if (color == 2) {
                    color = WHITE;
                }
                placeStone(x, y, color);
            }
            int blackCount = 0;
            int whiteCount = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] == BLACK) {
                        blackCount++;
                    } else if (map[i][j] == WHITE) {
                        whiteCount++;
                    }
                }
            }
            System.out.printf("#%d %d %d\n", test_case, blackCount, whiteCount);
        }

    }

    private static void placeStone(int x, int y, int color) {
        map[x][y] = color;
        for (int i = 0; i < 8; i++) {
            checkLevel(x, y, color, i);
        }
    }

    private static void checkLevel(int x, int y, int color, int direction) {
        int nextX = x;
        int nextY = y;
        List<int[]> otherColor = new ArrayList<>();
        while (true) {
            nextX += dirX[direction];
            nextY += dirY[direction];
            if (nextX >= 1 && nextX < map.length && nextY >= 1 && nextY < map[0].length
                    && map[nextX][nextY] == (color * -1)) {
                otherColor.add(new int[]{nextX, nextY});
            }
            if (nextX >= 1 && nextX < map.length && nextY >= 1 && nextY < map[0].length
                    && map[nextX][nextY] == color) {
                break;
            }
            if (nextX < 1 || (nextX >= map.length) || (nextY < 1) || (nextY >= map[0].length)
                    || (map[nextX][nextY] == 0)) {
                return;
            }
        }
        for (int[] changeIndex : otherColor) {
            map[changeIndex[0]][changeIndex[1]] = color;
        }
    }


}