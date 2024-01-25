import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    static Map<Integer, Integer>[] adjustNodes;

    static boolean[] visited;

    static int longestNode;

    static int diameter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        adjustNodes = new HashMap[Integer.parseInt(bf.readLine()) + 1];
        for (int i = 0; i < adjustNodes.length; i++) {
            adjustNodes[i] = new HashMap<>();
        }
        visited = new boolean[adjustNodes.length + 1];

        settingAdjustNodes(bf);
        dfs(1, 0);
        visited = new boolean[adjustNodes.length + 1];
        dfs(longestNode, 0);
        StringBuilder sb = new StringBuilder();
        sb.append(diameter);
        System.out.println(sb);
    }

    private static void settingAdjustNodes(BufferedReader bf) throws IOException {
        for (int i = 0; i < adjustNodes.length - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int startNodeIndex = Integer.parseInt(st.nextToken());
            while (true) {
                int nodeIndex = Integer.parseInt(st.nextToken());

                if (nodeIndex == -1) {
                    break;
                }
                int adjustLength = Integer.parseInt(st.nextToken());
                adjustNodes[startNodeIndex].put(nodeIndex, adjustLength);
            }
        }
    }

    private static void dfs(int node, int nowDiameter) {

        visited[node] = true;
        Map<Integer, Integer> adjustNode = adjustNodes[node];
        for (Entry<Integer, Integer> adjustNodeIndex : adjustNode.entrySet()) {
            if (!visited[adjustNodeIndex.getKey()]) {
                dfs(adjustNodeIndex.getKey(), nowDiameter + adjustNodeIndex.getValue());
            }
        }
        if (nowDiameter > diameter) {
            diameter = nowDiameter;
            longestNode = node;
        }
    }
}