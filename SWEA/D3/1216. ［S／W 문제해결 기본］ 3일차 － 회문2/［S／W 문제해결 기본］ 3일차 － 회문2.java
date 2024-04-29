import java.util.Scanner;

class Solution {
    static int maxLength;
    static int[] dir1 = {1, 0};
    static int[] dir2 = {0, 1};
    static char[][] map;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            maxLength = 1;
            int testNum = sc.nextInt();
            map = new char[100][100];
            for (int i = 0; i < 100; i++) {
                String str = sc.next();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = str.charAt(j);
                }
            }
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    String temp = String.valueOf(map[i][j]);
                    getMaxLength(temp, map, i, j);
                }
            }
            System.out.printf("#%d %d\n", testNum, maxLength);
        }
    }

    private static void getMaxLength(String str, char[][] map, int x, int y) {

        int nextRow = x;
        int nextCol = y;

        while (true) {
            nextRow += dir1[0];
            if (nextRow >= 0 && nextRow < 100) {
                if (isPellendarm1(map, x, y, nextRow)) {
                    maxLength = Math.max(maxLength, nextRow - x + 1);
                }
            } else {
                break;
            }
        }
        while (true) {
            nextCol += dir2[1];
            if (nextCol >= 0 && nextCol < 100) {
                if (isPellendarm2(map, x, y, nextCol)) {
                    maxLength = Math.max(maxLength, nextCol - y + 1);
                }
            } else {
                break;
            }
        }
    }

    private static boolean isPellendarm2(char[][] map, int x, int y, int nextCol) {
        int length = nextCol - y + 1;
        for (int i = 0; i < length / 2; i++) {
            if (map[x][y + i] != map[x][y + length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPellendarm1(char[][] map, int x, int y, int nextRow1) {
        int length = nextRow1 - x + 1;
        for (int i = 0; i < length / 2; i++) {
            if (map[x + i][y] != map[x + length - i - 1][y]) {
                return false;
            }
        }
        return true;
    }

}