import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isVisited;
    //    static Map<Integer, List<Integer>> nodes = new HashMap<>();
    static List<Integer>[] nodes;
    static int connectionGraph = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int vertexCount = Integer.parseInt(st.nextToken());
        isVisited = new boolean[vertexCount + 1];

        int edgeCount = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[vertexCount + 1];
        for (int i = 1; i <= vertexCount; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int vertexOne = Integer.parseInt(st.nextToken());
            int vertexTwo = Integer.parseInt(st.nextToken());
            nodes[vertexOne].add(vertexTwo);
            nodes[vertexTwo].add(vertexOne);
        }

//        for (Integer i : nodes.keySet()) {
//            if (!isVisited[i]) {
//                dfs(i);
//                if (!isAllCovered()) {
//                    connectionGraph++;
//                }
//            }
//        }
        for (int i = 1; i <= vertexCount; i++) {
            if (!isVisited[i]) {
                dfs(i);
                connectionGraph++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(connectionGraph);
        System.out.println(sb);


    }

    public static void dfs(int startIndex) {
        isVisited[startIndex] = true;
        List<Integer> integers = nodes[startIndex];
        for (Integer nextNode : integers) {
            if (!isVisited[nextNode]) {
                dfs(nextNode);
            }
        }
    }

    public static boolean isAllCovered() {
        for (int b = 1; b < isVisited.length; b++) {
            if (!isVisited[b]) {
                return false;
            }
        }
        return true;
    }

}