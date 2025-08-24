
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int startX = 0;
        int startY = 0;
        // init
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 2) {
                    startX = i;
                    startY = j;
                } else if (value == 1) {
                    map[i][j] = 1;
                }
            }
        }
        bfs(startX, startY);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    sb.append(-1).append(' ');
                } else {
                    sb.append(map[i][j]).append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void bfs(int startX, int startY) {
        visited = new boolean[N][M];
        visited[startX][startY] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny, poll[2] + 1});
                map[nx][ny] = poll[2] + 1;
            }
        }
    }
}
