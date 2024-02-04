import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] adjustNodes;
    static boolean[] visited;
    static int distance;

    static ArrayList<Integer> destinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int roadCount = Integer.parseInt(st.nextToken());
        distance = Integer.parseInt(st.nextToken());
        int startNodeIndex = Integer.parseInt(st.nextToken());

        adjustNodes = new ArrayList[nodeCount + 1];
        visited = new boolean[nodeCount + 1];
        for (int i = 1; i < adjustNodes.length; i++) {
            adjustNodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < roadCount; i++) {
            st = new StringTokenizer(bf.readLine());
            adjustNodes[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken())));
        }
        bfs(startNodeIndex, 0);

        if (destinations.isEmpty()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(destinations);
        for (Integer destination : destinations) {
            System.out.println(destination);
        }
    }


    private static void bfs(int node, int depth) {
        visited[node] = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(node));

        while (!queue.isEmpty()) {
            Node presentNode = queue.poll();
            for (Node adjustNode : adjustNodes[presentNode.getValue()]) {
                if (!visited[adjustNode.getValue()]) {
                    visited[adjustNode.getValue()] = true;
                    adjustNode.updateDepth(presentNode.getDepth() + 1);
                    if (adjustNode.getDepth() == distance) {
                        destinations.add(adjustNode.getValue());
                    }
                    queue.add(adjustNode);
                }
            }
        }
    }

    static class Node {
        int value;
        int depth;

        public int getValue() {
            return value;
        }

        public int getDepth() {
            return depth;
        }

        public void addDepth() {
            depth++;
        }

        public void updateDepth(int depth) {
            this.depth = depth;
        }

        public Node(int value) {
            this.value = value;
            this.depth = 0;
        }
    }
}