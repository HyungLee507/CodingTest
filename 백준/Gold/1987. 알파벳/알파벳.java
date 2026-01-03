
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    static int R, C;
    static int best = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        for (int i = 0; i < R; i++) {
            String line = bf.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }
        HashSet<Character> used = new HashSet<>();
        used.add((char) map[0][0]);
        dfs(0, 0, used);
        System.out.println(best);
    }

    static void dfs(int x, int y, HashSet<Character> used) {
        best = Math.max(best, used.size());

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            char nextCh = (char) map[nx][ny];

            if (used.contains(nextCh)) {
                continue;
            }
            used.add(nextCh);
            dfs(nx, ny, used);
            used.remove(nextCh);
        }
    }
}
