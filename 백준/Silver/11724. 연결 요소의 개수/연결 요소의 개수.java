import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isVisited;
    static Map<Integer, List<Integer>> nodes = new HashMap<>();

    static int connectionGraph = 1;


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int vertexCount = Integer.parseInt(st.nextToken());
        isVisited = new boolean[vertexCount + 1];
        int edgeCount = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= vertexCount; i++) {
            nodes.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int vertexOne = Integer.parseInt(st.nextToken());
            int vertexTwo = Integer.parseInt(st.nextToken());
            nodes.get(vertexOne).add(vertexTwo);
            nodes.get(vertexTwo).add(vertexOne);
        }

        for (Integer i : nodes.keySet()) {
            if (!isVisited[i]) {
                dfs(i);
                if (!isAllCovered()) {
                    connectionGraph++;
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        sb.append(connectionGraph);
        System.out.println(sb);


    }

    public static void dfs(int startIndex) {
        isVisited[startIndex] = true;
        List<Integer> integers = nodes.get(startIndex);
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