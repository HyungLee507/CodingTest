
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static String[] map;
    static int[][] dp;
    static boolean[][] visited;
    static boolean isInfinite;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new String[n];
        for (int i = 0; i < n; i++) {
            map[i] = bf.readLine();
        }
        isInfinite = false;
        dp = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int ans = dfs(0, 0);
        if (isInfinite) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }


    }
    //TODO : 0,0 에서 dfs() 시작
    // return 하는 값이 answer
    // 만약에 visited 한 부분에 다시 체크가 되면 무한 순환 된다??

    private static int dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length() || map[x].charAt(y) == 'H') {
            return 0;
        }

        if (visited[x][y]) {
            isInfinite = true;
            return 0;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        visited[x][y] = true;
        dp[x][y] = 0;
        int move = map[x].charAt(y) - '0';

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d] * move;
            int ny = y + dy[d] * move;
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }

        visited[x][y] = false;
        return dp[x][y];
    }


}
