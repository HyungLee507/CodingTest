
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, result;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][][] visited;
    static final int FIRE = 4;
    static final int WALL = -1;
    static List<Fire> fires;

    static class Fire {
        int x;
        int y;
        int time;

        public Fire(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Location {
        int x;
        int y;
        int cnt;

        public Location(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[2][N][M];
        Location jihoon = null;
        fires = new ArrayList<>();
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            String row = bf.readLine();
            for (int j = 0; j < M; j++) {
                if (row.charAt(j) == '#') {
                    map[i][j] = WALL;
                }
                if (row.charAt(j) == 'F') {
                    map[i][j] = FIRE;
                    fires.add(new Fire(i, j, 0));
                    visited[1][i][j] = true;
                }
                if (row.charAt(j) == 'J') {
                    jihoon = new Location(i, j, 0);
                }
            }
        }
        bfs(jihoon);
        if (result == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    private static void bfs(Location jihoon) {
        visited[0][jihoon.x][jihoon.y] = true;
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(jihoon);
        Queue<Fire> fireQueue = new ArrayDeque<>(fires);
        while (!queue.isEmpty()) {
            int size = fireQueue.size();
            for (int i = 0; i < size; i++) {
                Fire fire = fireQueue.poll();
                for (int j = 0; j < 4; j++) {
                    int nextX = fire.x + dx[j];
                    int nextY = fire.y + dy[j];
                    if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] != WALL
                            && !visited[1][nextX][nextY]) {
                        map[nextX][nextY] = FIRE;
                        visited[1][nextX][nextY] = true;
                        fireQueue.add(new Fire(nextX, nextY, fire.time + 1));
                    }
                }
            }
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Location now = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nextX = now.x + dx[j];
                    int nextY = now.y + dy[j];
                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                        result = now.cnt + 1;
                        return;
                    }
                    if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] != WALL
                            && map[nextX][nextY] != FIRE && !visited[0][nextX][nextY]) {
                        visited[0][nextX][nextY] = true;
                        queue.add(new Location(nextX, nextY, now.cnt + 1));
                    }
                }
            }
        }

    }
}
