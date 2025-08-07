
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    public static final int[] dx = {1, -1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while (true) {
            N = Integer.parseInt(bf.readLine());
            if (N == 0) {
                break;
            }
            int ans = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> {
                return Integer.compare(c1[2], c2[2]);
            });
            pq.offer(new int[]{0, 0, map[0][0]});
            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                visited[poll[0]][poll[1]] = true;
                if (poll[0] == N - 1 && poll[1] == N - 1) {
                    ans = poll[2];
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = poll[0] + dx[i];
                    int ny = poll[1] + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                        pq.offer(new int[]{nx, ny, poll[2] + map[nx][ny]});
                    }
                }
            }
            sb.append("Problem ").append(cnt++).append(": ").append(ans).append('\n');
        }

        System.out.println(sb);
    }
}
