
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static int[] parent;

    static class Load implements Comparable<Load> {
        int start;
        int end;
        int cost;

        public Load(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Load o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = IntStream.range(0, N + 1).toArray();
//        for (int i = 0; i <= N; i++) {
//            loads[i] = new ArrayList<>();
//        }
        PriorityQueue<Load> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Load(start, end, cost));
        }
        int ans = 0;
        int cnt = 0;
        int maxCost = 0;

        while (cnt < N - 1) {
            Load poll = pq.poll();
            if (find(poll.start) == find(poll.end)) {
                continue;
            }

            union(poll.start, poll.end);
            ans += poll.cost;
            maxCost = poll.cost;
            cnt++;
        }

        System.out.println(ans - maxCost);
    }

    private static boolean union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x != y) {
            parent[y] = x;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
