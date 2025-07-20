import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, P, K;
    static List<Edge>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        P = sc.nextInt();
        K = sc.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < P; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            graph[u].add(new Edge(v, cost));
            graph[v].add(new Edge(u, cost));
        }

        int left = -1;
        int right = 1_000_001;
        int answer = -1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                answer = mid;
                right = mid; 
            } else {
                left = mid;  
            }
        }
        if (right == 1_000_001) {
            System.out.println(-1);
        } else {
            System.out.println(right);
        }
        

    }

    static boolean isPossible(int X) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1, 0});  

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int cnt = cur[1];

            if (cnt > dist[now]) {
                continue;
            }

            for (Edge e : graph[now]) {
                int next = e.to;
                int nextCnt = cnt;

                if (e.cost > X) {
                    nextCnt++;  
                }

                if (nextCnt < dist[next]) {
                    dist[next] = nextCnt;
                    pq.offer(new int[]{next, nextCnt});
                }
            }
        }

        return dist[N] <= K;
    }
}
