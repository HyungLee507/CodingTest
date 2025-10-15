
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Robot implements Comparable<Robot> {
        int x;
        int y;
        int dir;
        int cnt;

        public Robot(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Robot o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        int[] start = new int[3];
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        start[2] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int[] end = new int[3];
        end[0] = Integer.parseInt(st.nextToken());
        end[1] = Integer.parseInt(st.nextToken());
        end[2] = Integer.parseInt(st.nextToken());
        int ans = bfs(start, end);
        System.out.println(ans);
    }

    private static int bfs(int[] start, int[] end) {
        int sx = start[0] - 1, sy = start[1] - 1, sd = start[2];
        int ex = end[0] - 1, ey = end[1] - 1, ed = end[2];

        Queue<Robot> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][N][M];

        q.offer(new Robot(sx, sy, sd, 0));
        visited[sd][sx][sy] = true;

        while (!q.isEmpty()) {
            Robot cur = q.poll();
            if (cur.x == ex && cur.y == ey && cur.dir == ed) {
                return cur.cnt;
            }

            for (int k = 1; k <= 3; k++) {
                int nx = cur.x + dx[cur.dir] * k;
                int ny = cur.y + dy[cur.dir] * k;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    break;
                }
                if (map[nx][ny] == 1) {
                    break;
                }

                if (!visited[cur.dir][nx][ny]) {
                    visited[cur.dir][nx][ny] = true;
                    q.offer(new Robot(nx, ny, cur.dir, cur.cnt + 1));
                }
            }

            int leftDir = turnLeft(cur.dir);
            if (!visited[leftDir][cur.x][cur.y]) {
                visited[leftDir][cur.x][cur.y] = true;
                q.offer(new Robot(cur.x, cur.y, leftDir, cur.cnt + 1));
            }
            int rightDir = turnRight(cur.dir);
            if (!visited[rightDir][cur.x][cur.y]) {
                visited[rightDir][cur.x][cur.y] = true;
                q.offer(new Robot(cur.x, cur.y, rightDir, cur.cnt + 1));
            }
        }
        return -1;
    }


    static int turnLeft(int d) {
        switch (d) {
            case 1:
                return 4;
            case 4:
                return 2;
            case 2:
                return 3;
            case 3:
                return 1;
        }
        return d;
    }

    static int turnRight(int d) {
        switch (d) {
            case 1:
                return 3;
            case 3:
                return 2;
            case 2:
                return 4;
            case 4:
                return 1;
        }
        return d;
    }
}
