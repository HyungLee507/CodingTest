import java.util.Objects;
import java.util.Scanner;

class Solution {

    static char[][] map;

    private static class Tank {
        int x;
        int y;
        int[] dir;

        private void changeDirection(char x) {
            switch (x) {
                case 'D':
                    this.dir = new int[]{1, 0};
                    break;
                case 'U':
                    this.dir = new int[]{-1, 0};
                    break;
                case 'L':
                    this.dir = new int[]{0, -1};
                    break;
                case 'R':
                    this.dir = new int[]{0, 1};
                    break;
            }
            int nextLocationX = this.x + this.dir[0];
            int nextLocationY = this.y + this.dir[1];
            if (!isWall(nextLocationX, nextLocationY)) {
                this.x = nextLocationX;
                this.y = nextLocationY;
            }
        }

        private static boolean isWall(int nextLocationX, int nextLocationY) {
            return nextLocationX < 0 || nextLocationX >= map.length || nextLocationY < 0
                    || nextLocationY >= map[0].length
                    || map[nextLocationX][nextLocationY] == '-' || map[nextLocationX][nextLocationY] == '*'
                    || map[nextLocationX][nextLocationY] == '#';
        }

        private void shooting() {
            int nextLocationX = this.x + dir[0];
            int nextLocationY = this.y + dir[1];
            while (nextLocationX >= 0 && nextLocationX < map.length && nextLocationY >= 0
                    && nextLocationY < map[0].length) {
                if (map[nextLocationX][nextLocationY] == '#') {
                    break;
                }
                if (map[nextLocationX][nextLocationY] == '*') {
                    map[nextLocationX][nextLocationY] = '.';
                    break;
                }
                nextLocationX += dir[0];
                nextLocationY += dir[1];
            }

        }

        public char getDirection() {

            if (dir[0] == 0 && dir[1] == 1) {
                return '>';
            }
            if (dir[0] == 1 && dir[1] == 0) {
                return 'v';
            }
            if (dir[0] == -1 && dir[1] == 0) {
                return '^';
            }
            return '<';
        }

        public Tank(int x, int y, char direction) {
            this.x = x;
            this.y = y;
            switch (direction) {
                case 'v':
                    this.dir = new int[]{1, 0};
                    break;
                case '^':
                    this.dir = new int[]{-1, 0};
                    break;
                case '<':
                    this.dir = new int[]{0, -1};
                    break;
                case '>':
                    this.dir = new int[]{0, 1};
                    break;
            }
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            map = new char[row][col];
            Tank tank = null;
            for (int i = 0; i < row; i++) {
                String temp = sc.next();
                for (int j = 0; j < col; j++) {
                    char x = temp.charAt(j);
                    if (x == '^' || x == '>' || x == '<' || x == 'v') {
                        map[i][j] = '.';
                        tank = new Tank(i, j, x);
                    } else {
                        map[i][j] = temp.charAt(j);
                    }
                }
            }
            int instrumentCount = sc.nextInt();
            String instrument = sc.next();
            for (int i = 0; i < instrumentCount; i++) {
                char order = instrument.charAt(i);
                if (order == 'S') {
                    Objects.requireNonNull(tank).shooting();
                } else if (order == 'D' || order == 'U' || order == 'L' || order == 'R') {
                    Objects.requireNonNull(tank).changeDirection(order);
                }
            }
//            StringBuilder sb = new StringBuilder();
//            sb.append('#').append(test_case).append(' ');
            System.out.printf("#%d ", test_case);
            map[Objects.requireNonNull(tank).x][tank.y] = tank.getDirection();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.printf("%c", map[i][j]);
//                    sb.append(map[i][j]);
                }
                System.out.println();
            }
//            System.out.println(sb);
        }
    }
}