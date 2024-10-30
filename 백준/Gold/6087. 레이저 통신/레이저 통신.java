import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[][][] cost;
    static List<int[]> endPoint;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int cnt;
        int dir;

        public Node(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        endPoint = new ArrayList<>();
        
        // init map and cost
        cost = new int[M][N][4];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        for (int i = 0; i < M; i++) {
            String temp = bf.readLine();
            for (int j = 0; j < N; j++) {
                char ch = temp.charAt(j);
                if (ch == '*') {
                    map[i][j] = -1;
                } else if (ch == 'C') {
                    endPoint.add(new int[]{i, j});
                }
            }
        }
        answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] start = endPoint.get(0);
        int[] end = endPoint.get(1);

        for (int i = 0; i < 4; i++) {
            queue.add(new Node(start[0], start[1], 0, i));
            cost[start[0]][start[1]][i] = 0;
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.x == end[0] && now.y == end[1]) {
                return now.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                int nextCnt = (now.dir == i) ? now.cnt : now.cnt + 1;

                if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N && map[nextX][nextY] == 0) {
                    if (cost[nextX][nextY][i] > nextCnt) {
                        cost[nextX][nextY][i] = nextCnt;
                        queue.add(new Node(nextX, nextY, nextCnt, i));
                    }
                }
            }
        }
        return -1;
    }
}
