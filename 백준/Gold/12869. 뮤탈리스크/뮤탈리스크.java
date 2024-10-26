import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] damage = {9, 3, 1};
    private static int[][][] dp;
    private static int[][] seq = {{0, 1, 2}, {0, 2, 1}, {1, 2, 0}, {1, 0, 2}, {2, 0, 1}, {2, 1, 0}};

    static int N;
    static int[] scv;

    static class Info {
        int[] unit;
        int depth;

        public Info(int[] unit, int depth) {
            this.unit = unit;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(bf.readLine());
        dp = new int[61][61][61];
        scv = new int[3];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                for (int k = 0; k < 61; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        dp[scv[0]][scv[1]][scv[2]] = 0;
        System.out.println(bfs(scv));
    }

    private static int bfs(int[] scv) {
        Queue<Info> queue = new ArrayDeque<>();
        queue.offer(new Info(scv, 0));
        while (!queue.isEmpty()) {
            Info poll = queue.poll();
            for (int i = 0; i < 6; i++) {
                int[] next = new int[3];
                for (int j = 0; j < N; j++) {
                    next[j] = Math.max(poll.unit[j] - damage[seq[i][j]], 0);
                }
                if (allDead(next)) {
                    return poll.depth + 1;
                }
                if (dp[next[0]][next[1]][next[2]] == -1) {
                    dp[next[0]][next[1]][next[2]] = dp[poll.unit[0]][poll.unit[0]][poll.unit[0]] + 1;
                    queue.offer(new Info(next, poll.depth + 1));
                }
            }
        }
        return -1;
    }

    private static boolean allDead(int[] unit) {
        for (int i = 0; i < N; i++) {
            if (unit[i] > 0) {
                return false;
            }
        }
        return true;
    }
}
