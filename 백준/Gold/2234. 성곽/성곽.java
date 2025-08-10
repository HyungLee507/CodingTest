
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int[] dx = {0, -1, 0, 1};
    public static final int[] dy = {-1, 0, 1, 0};
    static int[][] map, distribution;
    static Map<Integer, Integer> counter;
    static int N, M, maxRoom;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        distribution = new int[N][M];
        visited = new boolean[N][M];
        int idx = 0;
        maxRoom = 0;
        counter = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    int cnt = bfs(i, j, idx);
                    counter.put(idx++, cnt);
                    maxRoom = Math.max(maxRoom, cnt);
                }
            }
        }
        //TODO : 인접한 애들 붙여주는 방식으로 반복문 진행
        int maxDistribution = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxDistribution = Math.max(maxDistribution, getMaxDistribution(i, j));
            }
        }
        System.out.println(idx);
        System.out.println(maxRoom);
        System.out.println(maxDistribution);
    }

    private static int getMaxDistribution(int x, int y) {
        int res = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && distribution[nx][ny] != distribution[x][y]) {
                res = Math.max(counter.get(distribution[nx][ny]) + counter.get(distribution[x][y]), res);
            }
        }
        return res;
    }

    private static int bfs(int x, int y, int idx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        distribution[x][y] = idx;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]
                        && (map[poll[0]][poll[1]] & (1 << i)) == 0) {
                    visited[nx][ny] = true;
                    cnt++;
                    queue.offer(new int[]{nx, ny});
                    distribution[nx][ny] = idx;
                }
            }
        }
        return cnt;
    }
}
