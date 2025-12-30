
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adj;
    static boolean[] burned;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        int K = Integer.parseInt(br.readLine());
        burned = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int p = Integer.parseInt(st.nextToken());
            burned[p] = true;
        }

        // 1) 후보 폭탄 도시 찾기
        List<Integer> bombs = new ArrayList<>();
        for (int v = 1; v <= N; v++) {
            if (!burned[v]) continue;

            boolean ok = true;
            for (int u : adj[v]) {
                if (!burned[u]) { 
                    ok = false;
                    break;
                }
            }
            if (ok) bombs.add(v);
        }

        boolean[] covered = new boolean[N + 1];
        for (int b : bombs) {
            covered[b] = true;
            for (int u : adj[b]) covered[u] = true;
        }

        for (int v = 1; v <= N; v++) {
            if (covered[v] != burned[v]) {
                System.out.println(-1);
                return;
            }
        }

        Collections.sort(bombs);
        StringBuilder sb = new StringBuilder();
        sb.append(bombs.size()).append('\n');
        for (int i = 0; i < bombs.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(bombs.get(i));
        }
        sb.append('\n');
        System.out.print(sb);
    }
}
