
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjustNodes;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(bf.readLine());
        adjustNodes = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        dp = new int[N + 1][2];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjustNodes[start].add(end);
            adjustNodes[end].add(start);
        }
        dfs(0, 1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int parent, int node) {
        dp[node][0] = 0;
        dp[node][1] = 1;
        for (int next : adjustNodes[node]) {
            if (next == parent) {
                continue;
            }
            dfs(node, next);
            dp[node][0] += dp[next][1];
            dp[node][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}
