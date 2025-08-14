
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(bf.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int left = -1;
        int right = 201;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            visited = new boolean[N + 1][N + 1];
            if (search(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
    }

    static boolean search(int diff) {
        int start = map[1][1];
        int Lmin = Math.max(0, start - diff);
        int Lmax = Math.min(start, 200);

        for (int L = Lmin; L <= Lmax; L++) {
            int R = L + diff;
            if (bfs(L, R)) {
                return true;
            }
        }
        return false;
    }

    static boolean bfs(int L, int R) {
        if (map[1][1] < L || map[1][1] > R) {
            return false;
        }

        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<int[]> q = new LinkedList<>();
        visited[1][1] = true;
        q.offer(new int[]{1, 1});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0], y = poll[1];
            if (x == N && y == N) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 1 || nx > N || ny < 1 || ny > N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                int v = map[nx][ny];
                if (v < L || v > R) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return false;
    }

}
