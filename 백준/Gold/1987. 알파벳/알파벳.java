
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[][] map;
    static int best = 0;

    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j); // 'A'~'Z'
            }
        }

        int startIdx = ((char) map[0][0]) - 'A';
        int startMask = 1 << startIdx;

        dfs(0, 0, startMask, 1);

        System.out.println(best);
    }

    static void dfs(int x, int y, int mask, int depth) {
        if (depth > best) best = depth;
        if (best == 26) return; 

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

            int idx = ((char) map[nx][ny]) - 'A';
            int bit = 1 << idx;

            if ((mask & bit) != 0) continue; 

            dfs(nx, ny, mask | bit, depth + 1);
        }
    }
}
