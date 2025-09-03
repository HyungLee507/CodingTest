
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final int WALL = 1;

    static int[][] map;
    static int N, M, K;
    static int[] start, end;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '#') {
                    map[i][j] = WALL;
                }
            }
        }

        start = new int[2];
        end = new int[2];
        st = new StringTokenizer(bf.readLine());
        start[0] = Integer.parseInt(st.nextToken()) - 1;
        start[1] = Integer.parseInt(st.nextToken()) - 1;
        end[0] = Integer.parseInt(st.nextToken()) - 1;
        end[1] = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    static int bfs() {
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[start[0]][start[1]] = 0;
        q.add(new int[]{start[0], start[1]});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            if (x == end[0] && y == end[1]) {
                return dist[x][y];
            }

            for (int dir = 0; dir < 4; dir++) {
                for (int step = 1; step <= K; step++) {
                    int nx = x + dx[dir] * step;
                    int ny = y + dy[dir] * step;

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                        break;
                    }
                    if (map[nx][ny] == WALL) {
                        break;
                    }

                    int nd = dist[x][y] + 1;

                    if (dist[nx][ny] == -1) {
                        dist[nx][ny] = nd;
                        q.add(new int[]{nx, ny});
                    } else if (dist[nx][ny] < nd) {
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }
        return -1; 
    }
}
