import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static final int[] dx = {1, -1, 0, 0}; 
    static final int[] dy = {0, 0, 1, -1};
    static List<int[]> loc;
    static int[][] map;
    static int[][][] dist;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int dir;
        int cnt;

        public Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        dist = new int[N][N][4];
        loc = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < N; j++) {
                char temp = line.charAt(j);
                if (temp == '#') {
                    loc.add(new int[]{i, j});
                } else if (temp == '*') {
                    map[i][j] = 1;
                } else if (temp == '!') {
                    map[i][j] = 2;
                }
                for (int d = 0; d < 4; d++) {
                    dist[i][j][d] = Integer.MAX_VALUE;
                }
            }
        }

        int[] start = loc.get(0);
        int[] end = loc.get(1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int d = 0; d < 4; d++) {
            dist[start[0]][start[1]][d] = 0;
            pq.offer(new Node(start[0], start[1], d, 0));
        }

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (poll.x == end[0] && poll.y == end[1]) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (map[nx][ny] == 1) {
                    continue; 
                }

                if (poll.dir == d) {
                    if (dist[nx][ny][d] > poll.cnt) {
                        dist[nx][ny][d] = poll.cnt;
                        pq.offer(new Node(nx, ny, d, poll.cnt));
                    }
                }

                // 방향 전환 (현재 위치가 '!'이고, 직교 방향으로 꺾는 경우만)
                else if (map[poll.x][poll.y] == 2 &&
                        ((poll.dir <= 1 && d >= 2) || (poll.dir >= 2 && d <= 1))) {
                    if (dist[nx][ny][d] > poll.cnt + 1) {
                        dist[nx][ny][d] = poll.cnt + 1;
                        pq.offer(new Node(nx, ny, d, poll.cnt + 1));
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            min = Math.min(min, dist[end[0]][end[1]][d]);
        }
        System.out.println(min);
    }
}
