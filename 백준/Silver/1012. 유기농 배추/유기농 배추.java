import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] fields;
    static boolean[][] isVisited;

    static int worms = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int iterationCount = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int column = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int cabbages = Integer.parseInt(st.nextToken());
            fields = new boolean[row][column];
            isVisited = new boolean[row][column];
            for (int j = 0; j < cabbages; j++) {
                st = new StringTokenizer(bf.readLine());
                int cabbageCol = Integer.parseInt(st.nextToken());
                int cabbageRow = Integer.parseInt(st.nextToken());
                fields[cabbageRow][cabbageCol] = true;
            }
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    if (!isVisited[r][c] && fields[r][c]) {
                        dfs(r, c);
                        worms++;
                    }
                }
            }
            System.out.println(worms);
            worms = 0;
        }
    }

    private static void dfs(int x, int y) {
        int[] xLayer = new int[]{1, -1, 0, 0};
        int[] yLayer = new int[]{0, 0, 1, -1};
        int rowSize = fields.length;
        int colSize = fields[0].length;
        isVisited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = xLayer[i] + x;
            int nextY = yLayer[i] + y;
            if (nextX >= 0 && nextX < rowSize && nextY >= 0 && nextY < colSize && !isVisited[nextX][nextY]
                    && fields[nextX][nextY]) {
                dfs(nextX, nextY);
            }
        }

    }


}