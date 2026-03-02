
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 100_000;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(search());
    }

    static int search() {
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        dist[N] = 0;
        pq.offer(new int[]{N, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int cost = cur[1];

            if (cost != dist[now]) {
                continue;
            }

            if (now == M) {
                return cost;
            }

            int nx = now * 2;
            if (nx <= MAX && dist[nx] > cost) {
                dist[nx] = cost;
                pq.offer(new int[]{nx, cost});
            }

            nx = now - 1;
            if (nx >= 0 && dist[nx] > cost + 1) {
                dist[nx] = cost + 1;
                pq.offer(new int[]{nx, cost + 1});
            }

            nx = now + 1;
            if (nx <= MAX && dist[nx] > cost + 1) {
                dist[nx] = cost + 1;
                pq.offer(new int[]{nx, cost + 1});
            }
        }
        return -1;
    }
}