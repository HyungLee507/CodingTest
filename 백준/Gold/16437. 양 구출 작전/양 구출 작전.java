
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Node[] nodes;
    static boolean[] visited;
    static List<Integer>[] adjustNodes;

    static class Node {
        String state;
        long cnt;

        public Node(String state, long cnt) {
            this.state = state;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        nodes = new Node[N + 1];
        adjustNodes = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjustNodes[i] = new ArrayList<>();
        }

        nodes[1] = new Node(null, 0);

        for (int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String state = st.nextToken();
            long num = Long.parseLong(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(state, num);

            adjustNodes[i].add(parent);
            adjustNodes[parent].add(i);
        }

        visited = new boolean[N + 1];
        long ans = dfs(1);
        System.out.println(ans);
    }

    private static long dfs(int u) {
        visited[u] = true;

        long sum = 0L;
        for (int v : adjustNodes[u]) {
            if (!visited[v]) {
                sum += dfs(v);
            }
        }

        if (nodes[u] != null && nodes[u].state != null) {
            if ("S".equals(nodes[u].state)) {
                sum += nodes[u].cnt;
            } else {
                sum -= nodes[u].cnt;
                if (sum < 0) {
                    sum = 0;
                }
            }
        }
        return sum;
    }
}
