import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] land;
    static boolean[][] isVisited;
    static int[][] depths;
    static int row;
    static int column;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        land = new boolean[row][column];
        isVisited = new boolean[row][column];
        depths = new int[row][column];

        for (int i = 0; i < row; i++) {
            String line = bf.readLine();
            for (int j = 0; j < line.length(); j++) {
                land[i][j] = line.charAt(j) == '1';
            }
        }
        bfs(new int[]{0, 0});
    }

    static void bfs(int[] location) {
        int[] xLayer = new int[]{1, -1, 0, 0};
        int[] yLayer = new int[]{0, 0, 1, -1};
        Queue<int[]> bfsQueue = new ArrayDeque<>();
        depths[0][0] = 1;
        bfsQueue.add(location);
        while (!bfsQueue.isEmpty()) {
            int[] nextLocation = bfsQueue.poll();
            int nextX = nextLocation[0];
            int nextY = nextLocation[1];
            int nextDepth = depths[nextX][nextY];
            if (nextX == (row - 1) && nextY == (column - 1)) {
                System.out.println(nextDepth);
                return;
            }
            for (int i = 0; i < 4; i++) {
                if ((nextX + xLayer[i]) >= 0 && (nextX + xLayer[i]) < row && (nextY + yLayer[i]) >= 0
                        && (nextY + yLayer[i]) < column
                        && land[nextX + xLayer[i]][nextY + yLayer[i]] && !isVisited[nextX + xLayer[i]][nextY
                        + yLayer[i]]) {
                    bfsQueue.add(new int[]{nextX + xLayer[i], nextY + yLayer[i]});
                    depths[nextX + xLayer[i]][nextY + yLayer[i]] = nextDepth + 1;
                    isVisited[nextX + xLayer[i]][nextY + yLayer[i]] = true;
                }
            }
        }
    }


}