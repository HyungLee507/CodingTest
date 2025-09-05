
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int[] items;
    static List<Node>[] adjustNodes;
    static final int INF = 1_000_000_000;

    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n + 1];
        adjustNodes = new ArrayList[n + 1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            items[i + 1] = Integer.parseInt(st.nextToken());
            adjustNodes[i + 1] = new ArrayList<>();
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjustNodes[start].add(new Node(end, cost));
            adjustNodes[end].add(new Node(start, cost));
        }
        //다익스트라를 시작점으로부터 시작해서 업데이트
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dijkstra(i));
        }
        System.out.println(ans);
    }

    private static int dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) {
                continue;
            }

            for (Node next : adjustNodes[u]) {
                int nextDist = d + next.cost;
                if (nextDist < dist[next.end]) {
                    dist[next.end] = nextDist;
                    pq.offer(new int[]{next.end, nextDist});
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                sum += items[i];
            }
        }
        return sum;
    }

}
