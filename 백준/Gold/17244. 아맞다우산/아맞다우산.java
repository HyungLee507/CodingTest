
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, exitX, exitY;
    static int index = 1;
    static int[][] map;
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    public static final int EXIT = -1;
    public static final int WALL = -2;
    public static final int START = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < M; i++) {
            String line = bf.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'S') {
                    map[i][j] = START;
                    startX = i;
                    startY = j;
                } else if (line.charAt(j) == 'E') {
                    map[i][j] = EXIT;
                    exitX = i;
                    exitY = j;
                } else if (line.charAt(j) == '#') {
                    map[i][j] = WALL;
                } else if (line.charAt(j) == 'X') {
                    map[i][j] = index++;
                }
            }
        }
        System.out.println(bfs(startX, startY));
    }

    private static int bfs(int startX, int startY) {
        boolean[][][] visited = new boolean[1 << index][M][N];

        int resMask = 0;
        for (int i = 1; i < index; i++) {
            resMask |= (1 << i);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0, 0});
        visited[0][startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1], mask = poll[2], dist = poll[3];

            if (x == exitX && y == exitY && mask == resMask) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    continue;
                }
                if (map[nx][ny] == WALL) {
                    continue;
                }

                int nextMask = mask;
                if (1 <= map[nx][ny] && map[nx][ny] < index) {
                    nextMask |= (1 << map[nx][ny]);
                }

                if (!visited[nextMask][nx][ny]) {
                    visited[nextMask][nx][ny] = true;
                    queue.offer(new int[]{nx, ny, nextMask, dist + 1});
                }
            }
        }
        return 0;
    }
}
