import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[][][] dp;
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    static String RESULT;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        RESULT = bf.readLine();
        dp = new int[N][M][RESULT.length()];
        for (int[][] arr2 : dp) {
            for (int[] arr1 : arr2) {
                Arrays.fill(arr1, -1);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == RESULT.charAt(0)) {
                    ans += dfs(i, j, 0);
                }
            }
        }
        System.out.println(ans);
    }

    private static int dfs(int x, int y, int len) {
        if (len == RESULT.length() - 1) {
            return 1;
        }

        if (dp[x][y][len] != -1) {
            return dp[x][y][len];
        }
        int count = 0;
        for (int dir = 0; dir < 4; dir++) {
            for (int step = 1; step <= K; step++) {
                int nx = x + dx[dir] * step;
                int ny = y + dy[dir] * step;

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == RESULT.charAt(len + 1)) {
                    count += dfs(nx, ny, len + 1);
                }
            }
        }

        dp[x][y][len] = count;
        return count;

    }
}
