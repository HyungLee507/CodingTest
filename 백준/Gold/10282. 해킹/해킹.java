import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private static class Edge implements Comparable<Edge> {
        int target;
        long time;

        public Edge(int target, long time) {
            this.target = target;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.time, o.time);
        }
    }

    static long[] time;
    static List<Edge>[] adjustComputer;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int iterationCount = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjustComputer = new List[n + 1];
            time = new long[n + 1];
            Arrays.fill(time, Integer.MAX_VALUE);
            for (int j = 1; j <= n; j++) {
                adjustComputer[j] = new ArrayList<>();
            }
            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                adjustComputer[b].add(new Edge(a, s));
            }
            time[c] = 0;
            dks(c);
            int count = 0;
            int totalTime = 0;
            for (int j = 1; j < time.length; j++) {
                if (time[j] != Integer.MAX_VALUE) {
                    totalTime = Integer.max(totalTime, (int) time[j]);
                    count++;
                }
            }
            System.out.printf("%d %d\n", count, totalTime);
        }


    }

    private static void dks(int start) {
        Queue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            int now = poll.target;
            for (Edge edge : adjustComputer[now]) {
                long newTime = time[now] + edge.time;
                if (newTime < time[edge.target]) {
                    time[edge.target] = Long.min(time[edge.target], time[now] + edge.time);
                    queue.add(new Edge(edge.target, time[edge.target]));
                }
            }
        }
    }

}


