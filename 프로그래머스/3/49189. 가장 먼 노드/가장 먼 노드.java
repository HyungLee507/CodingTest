import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    List<Integer>[] graph;

    public int solution(int n, int[][] edge) {

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        int[] dist = dijkstra(1, n);

        int maxDist = 0;

        for (int i = 1; i <= n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) {
                answer++;
            }
        }

        return answer;
    }

    private int[] dijkstra(int start, int n) {

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            if (now.cost > dist[now.idx]) {
                continue;
            }

            for (int next : graph[now.idx]) {

                int nextCost = now.cost + 1;

                if (nextCost < dist[next]) {
                    dist[next] = nextCost;

                    pq.offer(
                        new Node(next, nextCost)
                    );
                }
            }
        }

        return dist;
    }
}