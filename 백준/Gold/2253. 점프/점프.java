import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[] blocked;
    static int[][] dist;
    static final int INF = 1_000_000_000;
    static int maxJump;

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blocked = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int small = Integer.parseInt(bf.readLine());
            if (small <= N) {
                blocked[small] = true;
            }
        }
        if (N == 1) {
            System.out.println(0);
            return;
        }
        if (blocked[2]) {
            System.out.println(-1);
            return;
        }
        maxJump = (int) Math.sqrt(2 * N) + 2;
        dist = new int[N + 1][maxJump + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }
        bfs();

        int answer = INF;
        for (int j = 1; j <= maxJump; j++) {
            answer = Math.min(answer, dist[N][j]);
        }

        System.out.println(answer == INF ? -1 : answer);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        dist[2][1] = 1;
        queue.offer(new int[]{2, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pos = cur[0];
            int lastJump = cur[1];

            for (int d = -1; d <= 1; d++) {

                int nextJump = lastJump + d;

                if (nextJump <= 0 || nextJump > maxJump) {
                    continue;
                }

                int nextPos = pos + nextJump;

                if (nextPos > N) {
                    continue;
                }

                if (blocked[nextPos]) {
                    continue;
                }

                if (dist[nextPos][nextJump] > dist[pos][lastJump] + 1) {
                    dist[nextPos][nextJump] = dist[pos][lastJump] + 1;
                    queue.offer(new int[]{nextPos, nextJump});
                }
            }
        }
    }
}