
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] points;
    static int[] adjustNodes;
    static boolean[] visited, finished;
    static int N;
    static int cycleCount;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int iterCnt = Integer.parseInt(bf.readLine());
        for (int i = 0; i < iterCnt; i++) {
            N = Integer.parseInt(bf.readLine());
            points = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            adjustNodes = new int[N + 1];
            st = new StringTokenizer(bf.readLine());
            cycleCount = 0;
            for (int j = 1; j < N + 1; j++) {
                int next = Integer.parseInt(st.nextToken());
                adjustNodes[j] = next;
            }
            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    dfs(j);
                }
            }
            sb.append(N - cycleCount).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int u) {
        visited[u] = true;
        int v = adjustNodes[u];

        if (!visited[v]) {
            dfs(v);
        } else if (!finished[v]) {
            int cnt = 1;
            for (int w = v; adjustNodes[w] != v; w = adjustNodes[w]) {
                cnt++;
            }
            cycleCount += cnt;
        }

        finished[u] = true;
    }
}
