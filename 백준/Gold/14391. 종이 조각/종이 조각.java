
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map, mask;
    static boolean[][] visited;
    public static final int[] dx = {1, -1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        mask = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        ans = 0;
        visited = new boolean[N][M];
        dfs(0, 0);
        System.out.println(ans);
    }

    private static void dfs(int depth, int sum) {
        if (depth == N * M) {
            ans = Math.max(sum, ans);
            return;
        }
        int x = depth / M;
        int y = depth % M;
        if (visited[x][y]) {
            dfs(depth + 1, sum);
        } else {
            int num = 0;
            visited[x][y] = true;
            num = map[x][y];
            dfs(depth + 1, sum + num);

            int i, temp = num;
            for (i = x + 1; i < N; i++) {
                if (visited[i][y]) {
                    break;
                }
                visited[i][y] = true;
                temp = temp * 10 + map[i][y];
                dfs(depth + 1, sum + temp);
            }

            for (int j = x + 1; j < i; j++) {
                visited[j][y] = false;
            }

            temp = num;
            for (i = y + 1; i < M; i++) {
                if (visited[x][i]) {
                    break;
                }
                visited[x][i] = true;
                temp = temp * 10 + map[x][i];
                dfs(depth + i - y + 1, sum + temp);
            }

            for (int j = y + 1; j < i; j++) {
                visited[x][j] = false;
            }
            visited[x][y] = false;
        }
    }
}
