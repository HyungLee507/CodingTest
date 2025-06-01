
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000;
    static int N, M;
    static int[][] map, dist;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Node implements Comparable<Node> {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());    // 가로 크기
        N = Integer.parseInt(st.nextToken());    // 세로 크기
        map = new int[N + 1][M + 1];
        dist = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = (line.charAt(j - 1) == '1' ? 1 : 0);
                dist[i][j] = INF;  // 초기값: 충분히 큰 수
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1][1] = 0;
        pq.offer(new Node(1, 1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.x][cur.y]) {
                continue;
            }
            if (cur.x == N && cur.y == M) {
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 1 || nx > N || ny < 1 || ny > M) {
                    continue;
                }

                int nextCost = cur.cost + (map[nx][ny] == 1 ? 1 : 0);
                if (nextCost < dist[nx][ny]) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Node(nx, ny, nextCost));
                }
            }
        }

        System.out.println(dist[N][M]);
    }
}
