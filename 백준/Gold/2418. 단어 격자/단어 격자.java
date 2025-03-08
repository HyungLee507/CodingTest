import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};
    static int h, w, l;
    static String[] grid;
    static long result;
    static long[][][] dp;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        grid = new String[h];
        dp = new long[l][h][w];
        
        for (int i = 0; i < h; i++) {
            grid[i] = bf.readLine();
        }
        str = bf.readLine();
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i].charAt(j) == str.charAt(0)) {
                    dp[0][i][j] = 1;
                    queue.add(new int[]{i, j, 0}); 
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], depth = cur[2];
            if (depth == l - 1) continue; 
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (grid[nx].charAt(ny) == str.charAt(depth + 1)) {
                    if (dp[depth + 1][nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, depth + 1});
                    }
                    dp[depth + 1][nx][ny] += dp[depth][x][y];
                }
            }
        }
        
        for (int i = 0; i < h; i++) {
            result += Arrays.stream(dp[l - 1][i]).sum();
        }
        System.out.println(result);
    }
}
