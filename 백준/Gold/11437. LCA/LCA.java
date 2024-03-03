import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] adjustNodes;
    static boolean[] visited;
    static int[] parents;
    static int[] depths;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nodeCount = Integer.parseInt(bf.readLine());
        visited = new boolean[nodeCount + 1];
        parents = new int[nodeCount + 1];
        depths = new int[nodeCount + 1];
        adjustNodes = new ArrayList[nodeCount + 1];
        for (int i = 0; i < nodeCount + 1; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < nodeCount - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adjustNodes[node1].add(node2);
            adjustNodes[node2].add(node1);
        }
        dfs(1, 0);
//        bfs(1);
        int lcaCount = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lcaCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int commonAncestor = lca(node1, node2);
            sb.append(commonAncestor).append('\n');
        }
        System.out.println(sb);
    }

    public static int lca(int a, int b) {
        while (a != b) {
            if (depths[a] == depths[b]) {
                a = parents[a];
                b = parents[b];
            } else if (depths[a] > depths[b]) {
                a = parents[a];
            } else {
                b = parents[b];
            }
        }
        return a;
//        if (parents[node1] < parents[node2]) {
//            int temp = node1;
//            node1 = node2;
//            node2 = temp;
//        }
//        while (depths[node1] != depths[node2]) {
//            node1 = parents[node1];
//        }
//        while (node1 != node2) {
//            node1 = parents[node1];
//            node2 = parents[node2];
//        }
//        return node1;
    }

    public static void dfs(int index, int depth) {
        depths[index] = depth;
        visited[index] = true;
        for (int i : adjustNodes[index]) {
            if (!visited[i]) {
                parents[i] = index;
                dfs(i, depth + 1);
            }
        }
    }

//    public static void bfs(int node) {
//        Queue<Integer> queue = new ArrayDeque<>();
//        queue.add(node);
//        visited[node] = true;
//        int level = 1;
//        int now_size = 1;
//        int count = 0;
//        while (!queue.isEmpty()) {
//            int now_node = queue.poll();
//            for (Integer next : adjustNodes[now_node]) {
//                if (!visited[next]) {
//                    visited[next] = true;
//                    queue.add(next);
//                    parents[next] = now_node;
//                    depths[next] = level;
//                }
//            }
//            count++;
//            if (count == now_size) {
//                count = 0;
//                now_size = queue.size();
//                level++;
//            }
//        }
//    }

}