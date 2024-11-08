import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {1, 0, 0, -1, 0, 0};
    static final int[] dy = {0, 1, -1, 0, 0, 0};
    static final int[] dz = {0, 0, 0, 0, 1, -1};

    static int[][][] map;
    static boolean[][][] visited;
    static int[] start;
    static int[] end;
    static int L;
    static int R;
    static int C;

    private static class Location {
        int x;
        int y;
        int z;
        int count;

        public Location(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(
                System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(bf.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            // init
            map = new int[L][R][C];
            start = new int[3];
            end = new int[3];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String temp = bf.readLine();
                    for (int k = 0; k < C; k++) {
                        if (temp.charAt(k) == '#') {
                            map[i][j][k] = -1;
                        }
                        if (temp.charAt(k) == 'E') {
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                        }
                        if (temp.charAt(k) == 'S') {
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        }
                    }
                }
                bf.readLine();
            }
            int result = bfs(start);
            if (result == -1) {
                sb.append("Trapped!").append('\n');
            } else {
                sb.append("Escaped in ").append(result).append(" minute(s).").append('\n');
            }
        }
        bf.close();
        System.out.println(sb);
    }

    private static int bfs(int[] start) {
        Queue<Location> queue = new ArrayDeque<>();
        visited = new boolean[L][R][C];
        queue.add(new Location(start[0], start[1], start[2], 0));
        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            if (poll.x == end[0] && poll.y == end[1] && poll.z == end[2]) {
                return poll.count;
            }
            for (int i = 0; i < 6; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                int nz = poll.z + dz[i];
                if (nx >= 0 && nx < L && ny >= 0 && ny < R && nz >= 0 && nz < C && !visited[nx][ny][nz]
                        && map[nx][ny][nz] != -1) {
                    visited[nx][ny][nz] = true;
                    queue.offer(new Location(nx, ny, nz, poll.count + 1));
                }
            }
        }
        return -1;
    }
}