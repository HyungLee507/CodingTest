
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] finished;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String temp = bf.readLine();
            for (int j = 0; j < M; j++) {
                char ch = temp.charAt(j);
                if (ch == 'U') {
                    map[i][j] = 0;
                } else if (ch == 'D') {
                    map[i][j] = 1;
                } else if (ch == 'L') {
                    map[i][j] = 2;
                } else {
                    map[i][j] = 3;
                }
            }
        }

        visited = new boolean[N][M];
        finished = new boolean[N][M];

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    answer += search(i, j);
                }
            }
        }
        System.out.println(answer);
    }

    private static int search(int sx, int sy) {
        Deque<int[]> stack = new ArrayDeque<>();
        int x = sx, y = sy;

        while (true) {
            if (visited[x][y]) {
                int madeCycle = finished[x][y] ? 0 : 1;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    finished[pop[0]][pop[1]] = true;
                }
                return madeCycle;
            }

            visited[x][y] = true;
            stack.push(new int[]{x, y});

            int d = map[x][y];
            x += dx[d];
            y += dy[d];
        }
    }
}
