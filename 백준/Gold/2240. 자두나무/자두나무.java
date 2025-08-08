
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, W;
    static int[][] dp;
    static int[] dropLocation;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        dropLocation = new int[T];
        for (int i = 0; i < T; i++) {
            dropLocation[i] = Integer.parseInt(bf.readLine());
        }
        dp = new int[T][W + 1];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int time, int cnt) {
        if (time == T) {
            return 0;
        }

        if (dp[time][cnt] != -1) {
            return dp[time][cnt];
        }

        int pos = (cnt % 2 == 0) ? 1 : 2;
        int catchFruit = (dropLocation[time] == pos) ? 1 : 0;

        int stay = dfs(time + 1, cnt) + catchFruit;

        int move = 0;
        if (cnt < W) {
            int nextPos = (pos == 1) ? 2 : 1;
            int movedCatchFruit = (dropLocation[time] == nextPos) ? 1 : 0;
            move = dfs(time + 1, cnt + 1) + movedCatchFruit;
        }
        return dp[time][cnt] = Math.max(stay, move);
    }
}
