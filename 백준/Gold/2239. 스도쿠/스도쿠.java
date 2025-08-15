
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] grid;
    static boolean[][] squareVisited;
    static boolean[][] rowVisited;
    static boolean[][] colVisited;
    static boolean solved = false;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        grid = new int[9][9];
        squareVisited = new boolean[9][10];
        rowVisited = new boolean[9][10];
        colVisited = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            String line = bf.readLine();
            for (int j = 0; j < 9; j++) {
                int num = line.charAt(j) - '0';
                grid[i][j] = num;
                if (num != 0) {
                    int idx = (i / 3) * 3 + j / 3;
                    squareVisited[idx][num] = true;
                    rowVisited[i][num] = true; // 가로
                    colVisited[j][num] = true; // 세로
                }
            }
        }
        dfs(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(grid[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int idx) {
        if (idx == 81) {
            solved = true;
            return;
        }
        int r = idx / 9, c = idx % 9;
        if (grid[r][c] != 0) {
            dfs(idx + 1);
            return;
        }

        int b = (r / 3) * 3 + (c / 3);
        for (int d = 1; d <= 9; d++) {
            if (rowVisited[r][d] || colVisited[c][d] || squareVisited[b][d]) {
                continue;
            }

            grid[r][c] = d;
            rowVisited[r][d] = colVisited[c][d] = squareVisited[b][d] = true;

            dfs(idx + 1);
            if (solved) {
                return;
            }
            grid[r][c] = 0;
            rowVisited[r][d] = colVisited[c][d] = squareVisited[b][d] = false;
        }
    }
}
