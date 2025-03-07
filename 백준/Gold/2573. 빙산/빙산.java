
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<int[]> queue;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        queue = new LinkedList<>();
        // init
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                int ice = Integer.parseInt(st.nextToken());
                map[i][j] = ice;
                queue.add(new int[]{i, j, 0});
            }
        }
        int answer = 0;
        for (int i = 1; i < 10000; i++) {
            meltIceberg();
            if (queue.isEmpty()) {
                break;
            }
            if (isDivided()) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    private static boolean isDivided() {
        int cnt = 1;
        int[] first = queue.peek();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new int[]{first[0], first[1]});
        visited[first[0]][first[1]] = true;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                if (nx < n && nx >= 0 && ny >= 0 && ny < m && map[nx][ny] != 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        if (cnt == queue.size()) {
            return false;
        }
        return true;
    }

    private static void meltIceberg() {
        int size = queue.size();
        // 한번 싹다 돌려
        for (int i = 0; i < queue.size(); i++) {
            int[] poll = queue.poll();
            poll[2] = getWaterCount(poll[0], poll[1]);
            queue.add(poll);
        }
        for (int i = 0; i < size; i++) {
            int[] poll = queue.poll();
            map[poll[0]][poll[1]] -= poll[2];
            if (map[poll[0]][poll[1]] < 0) {
                map[poll[0]][poll[1]] = 0;
            }
            if (map[poll[0]][poll[1]] > 0) {
                poll[2] = 0;
                queue.add(poll);
            }
        }
    }

    private static int getWaterCount(int x, int y) {

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < n && nx >= 0 && ny >= 0 && ny < m && map[nx][ny] == 0) {
                cnt++;
            }
        }
        return cnt;
    }

}
