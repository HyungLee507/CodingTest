import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static List<Integer>[] graph;
    static int[] parents;
    static int[] depth;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        for (int n = 1; n <= N; n++) {
            graph[n] = new ArrayList<>();
        }
        parents = new int[N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);

        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            while (a != b) {
                if (depth[a] == depth[b]) {
                    a = parents[a];
                    b = parents[b];
                } else if (depth[a] > depth[b]) {
                    a = parents[a];
                } else {
                    b = parents[b];
                }
            }

            sb.append(a).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int v, int d) {
        depth[v] = d;
        visited[v] = true;

        for (int child : graph[v]) {
            if (visited[child]) {
                continue;
            }
            parents[child] = v;
            dfs(child, d + 1);
        }
    }
}