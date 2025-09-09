
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    public static final int MOD = 10007;
    static int N, M, H;
    static List<Integer>[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        blocks = new List[N + 1];
        // init 부분
        for (int i = 1; i < N + 1; i++) {
            blocks[i] = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        dp = new int[N + 1][H + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(1, 0));
    }

    private static int dfs(int idx, int height) {
        if (height == H) {
            return 1;
        }
        if (idx > N) {
            return 0;
        }

        if (dp[idx][height] != -1) {
            return dp[idx][height];
        }

        int res = 0;

        res = (res + dfs(idx + 1, height)) % MOD;

        for (int h : blocks[idx]) {
            int nh = height + h;
            if (nh <= H) {
                res = (res + dfs(idx + 1, nh)) % MOD;
            }
        }

        return dp[idx][height] = res;
    }
}
