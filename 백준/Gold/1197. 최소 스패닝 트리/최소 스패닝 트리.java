import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int startIndex;
        int endIndex;
        int cost;

        public Edge(int startIndex, int endIndex, int cost) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, nodeCount + 1).toArray();
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(startIndex, endIndex, cost));
        }
        int weightSum = 0;

        while (nodeCount > 1) {
            Edge poll = edges.poll();
            int startIndex = poll.startIndex;
            int endIndex = poll.endIndex;
            if (find(startIndex) == find(endIndex)) {
                continue;
            }
            union(startIndex, endIndex);
            weightSum += poll.cost;
            nodeCount--;

        }
        System.out.println(weightSum);


    }

    static void union(int value1, int value2) {

        int first = find(value1);
        int second = find(value2);
        if (first != second) {
            parents[find(value2)] = find(value1);
        }
    }

    static int find(int value) {
        if (value == parents[value]) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }

}