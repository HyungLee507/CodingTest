import java.util.Scanner;

class Solution {
    private static final int[] dirX = {1, 1, 0, 1};
    //    private static final int[] dirX = {1, 1, 0, 1, 0, -1, -1, -1, 1};
    private static final int[] dirY = {-1, 0, 1, 1};
    //    private static final int[] dirY = {-1, 0, 1, 1, -1, 1, 0, -1};
    static char[][] board;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            board = new char[n][n];
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                for (int j = 0; j < line.length(); j++) {
                    board[i][j] = line.charAt(j);
                }
            }

            if (isConnected()) {
                System.out.printf("#%d YES\n", test_case);
            } else {
                System.out.printf("#%d NO\n", test_case);
            }
        }
    }

    private static boolean isConnected() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'o' && dfs(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int count = 1;
            int nextX = x;
            int nextY = y;
            for (int j = 0; j < 4; j++) {
                nextX += dirX[i];
                nextY += dirY[i];
                if (nextX >= board.length || nextX < 0 || nextY >= board.length || nextY < 0
                        || board[nextX][nextY] != 'o') {
                    break;
                }
                count++;
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }


}
