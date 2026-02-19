
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][][] visited;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static int N, M, answer;


    private static class Location {
        int x;
        int y;
        int cnt;
        int useDrill;

        public Location(int x, int y, int cnt, int useDrill) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.useDrill = useDrill;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            String temp = bf.readLine();
            for (int j = 1; j <= M; j++) {
                if (temp.charAt(j - 1) == '1') {
                    map[i][j] = 1;
                }
            }
        }
        bfs();
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void bfs() {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(new Location(1, 1, 0, 0));
        visited = new boolean[2][N + 1][M + 1];
        visited[0][1][1] = true;
        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            if (poll.x == N && poll.y == M) {
                answer = poll.cnt + 1;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = poll.x + dirX[i];
                int nextY = poll.y + dirY[i];
                if (nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= M && !visited[poll.useDrill][nextX][nextY]) {
                    if (map[nextX][nextY] == 0) {
                        queue.offer(new Location(nextX, nextY, poll.cnt + 1, poll.useDrill));
                        visited[poll.useDrill][nextX][nextY] = true;
                    } else if (map[nextX][nextY] == 1 && poll.useDrill == 0 && !visited[1][nextX][nextY]) {
                        queue.offer(new Location(nextX, nextY, poll.cnt + 1, 1));
                        visited[1][nextX][nextY] = true;
                    }
                }
            }
        }

    }
}
