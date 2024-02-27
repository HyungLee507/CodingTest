import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] adjustNodes;

    static int deleteNodeIndex;
    static int leafNodes = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(bf.readLine());
        adjustNodes = new ArrayList[nodes];
        visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int rootNode = 0;
        for (int i = 0; i < nodes; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                rootNode = i;
                continue;
            }
            adjustNodes[i].add(parent);
            adjustNodes[parent].add(i);
        }
        deleteNodeIndex = Integer.parseInt(bf.readLine());

        if (deleteNodeIndex == rootNode) {
            System.out.println(0);
            return;
        }
        dfs(rootNode);
        System.out.println(leafNodes);

    }

    static void dfs(int index) {

        if (visited[index]) {
            return;
        }
        visited[index] = true;
        int nodeCount = 0;
        List<Integer> adjustNode = adjustNodes[index];
        for (Integer i : adjustNode) {
            if (!visited[i] && i != deleteNodeIndex) {
                dfs(i);
                nodeCount++;
            }
        }
        if (nodeCount == 0) {
            leafNodes++;
        }

    }


}