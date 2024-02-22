import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static long[] distance;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        edges = new Edge[edgeCount + 1];
        for (int i = 1; i <= edgeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i - 1] = new Edge(start, end, cost);
        }
        distance = new long[nodeCount + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        for (int i = 0; i < nodeCount - 1; i++) {
            for (int j = 0; j < edgeCount; j++) {
                Edge edge = edges[j];
                if (distance[edge.start] != Integer.MAX_VALUE
                        && distance[edge.end] > edge.cost + distance[edge.start]) {
                    distance[edge.end] = edge.cost + distance[edge.start];
                }
            }
        }
        boolean isNegativeCycle = false;
        for (int j = 0; j < edgeCount; j++) {
            Edge edge = edges[j];
            if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > edge.cost + distance[edge.start]) {
                isNegativeCycle = true;
            }
        }
        if (!isNegativeCycle) {
            for (int i = 2; i <= nodeCount; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(distance[i]);
                }
            }
        } else {
            System.out.println(-1);
        }


    }


}