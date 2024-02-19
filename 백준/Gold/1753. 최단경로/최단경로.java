import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] adjustNodes;
    static int[] distance;
    static boolean[] visited;


    static class Node implements Comparable<Node> {
        int index;
        int cost;
        
        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.cost, node.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int vertexes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int startIndex = Integer.parseInt(bf.readLine());

        adjustNodes = new List[vertexes + 1];
        for (int i = 0; i <= vertexes; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        distance = new int[vertexes + 1];
        visited = new boolean[vertexes + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startIndex] = 0;

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(bf.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjustNodes[firstNode].add(new Node(endNode, cost));
        }
        dijkstra(startIndex);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= vertexes; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                sb.append(distance[i]).append('\n');
            } else {
                sb.append("INF").append('\n');
            }
        }
        System.out.println(sb);


    }

    public static void dijkstra(int startNode) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startNode, 0));
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (visited[now.index]) {
                continue;
            }
            visited[now.index] = true;
            for (Node next : adjustNodes[now.index]) {
                if (distance[now.index] + next.cost < distance[next.index]) {
                    distance[next.index] = distance[now.index] + next.cost;
                    queue.add(new Node(next.index, distance[next.index]));
                }
            }
        }
    }
}