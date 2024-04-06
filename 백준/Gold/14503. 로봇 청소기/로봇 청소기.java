import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int[][] room;
    static boolean[][] visited;
    static int row;
    static int col;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        inputRoomSize(bf);
        int[] initRobotData = initRobotData(bf);
        RoboticVacuum robot = new RoboticVacuum(initRobotData[0], initRobotData[1], getDir(initRobotData[2]));
        initRoom(bf);
        System.out.println(robot.run());
    }

    private static void initRoom(BufferedReader bf) throws IOException {
        for (int i = 0; i < row; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < col; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void inputRoomSize(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        room = new int[row][col];
        visited = new boolean[row][col];
    }

    public static int[] initRobotData(BufferedReader bf) throws IOException {
        int[] robotData = new int[3];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        robotData[0] = Integer.parseInt(st.nextToken());
        robotData[1] = Integer.parseInt(st.nextToken());
        robotData[2] = Integer.parseInt(st.nextToken());
        return robotData;
    }

    private static int[] getDir(int dirIndex) {
        if (dirIndex == NORTH) {
            return DIR[NORTH];
        }
        if (dirIndex == EAST) {
            return DIR[EAST];
        }
        if (dirIndex == WEST) {
            return DIR[WEST];
        }
        if (dirIndex == SOUTH) {
            return DIR[SOUTH];
        }
        return null;
    }

    private static class RoboticVacuum {
        int x;
        int y;
        int[] dir;

        public RoboticVacuum(int x, int y, int[] dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        private int run() {
            int roomCount = 0;
            while (true) {
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    roomCount++;
                }
                if (haveCleaningRoom()) {
                    //todo : 방향 돌리기 (90도)
                    turnDir();
                    //todo : 방향에 청소되지 않은 경우 전진
                    forwarding();
                } else {
                    if (isWall()) {
                        break;
                    }
                    moveBack();
                }
            }
            return roomCount;
        }

        private void forwarding() {
            int forwardX = x + dir[0];
            int forwardY = y + dir[1];
            if (forwardX >= 0 && forwardX < row && forwardY >= 0 && forwardY < col && !visited[forwardX][forwardY]
                    && room[forwardX][forwardY] == 0) {
                x = forwardX;
                y = forwardY;
            }
        }

        private void turnDir() {
            if (Arrays.equals(dir, DIR[NORTH])) {
                dir = Arrays.copyOf(DIR[WEST], 2);
                return;
            }
            if (Arrays.equals(dir, DIR[EAST])) {
                dir = Arrays.copyOf(DIR[NORTH], 2);
                return;
            }
            if (Arrays.equals(dir, DIR[SOUTH])) {
                dir = Arrays.copyOf(DIR[EAST], 2);
                return;
            }
            if (Arrays.equals(dir, DIR[WEST])) {
                dir = Arrays.copyOf(DIR[SOUTH], 2);
                return;
            }
        }

        private boolean isWall() {
            int backLocationX = x + dir[0] * -1;
            int backLocationY = y + dir[1] * -1;
            if (backLocationX < 0 || backLocationX >= row || backLocationY < 0 || backLocationY >= col
                    || room[backLocationX][backLocationY] == 1) {
                return true;
            }
            return false;
        }

        private void moveBack() {
            x = x + dir[0] * -1;
            y = y + dir[1] * -1;
        }

        private boolean haveCleaningRoom() {
            for (int i = 0; i < 4; i++) {
                int dirX = DIR[i][0] + x;
                int dirY = DIR[i][1] + y;
                if (dirX >= 0 && dirX < row && dirY >= 0 && dirY < col && room[dirX][dirY] == 0
                        && !visited[dirX][dirY]) {
                    return true;
                }
            }
            return false;
        }
    }


}