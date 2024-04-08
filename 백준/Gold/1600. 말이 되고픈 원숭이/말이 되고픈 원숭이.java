import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[][] map;
    static boolean[][][] visitedStep;
    static int row;
    static int col;
    public static final int FIELD = 0;
    public static final int OBSTACLE = 1;
    public static final int[] HORSE_STEP_X = {2, 2, 1, 1, -1, -1, -2, -2};
    public static final int[] HORSE_STEP_Y = {-1, 1, 2, -2, 2, -2, 1, -1};

    public static final int[] MONKEY_STEP_X = {1, -1, 0, 0};
    public static final int[] MONKEY_STEP_Y = {0, 0, -1, 1};

    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        initMap(bf);
        search();
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    private static void search() {
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(0, 0, 0, 0));
        visitedStep[0][0][0] = true;
        while (!queue.isEmpty()) {
            Step poll = queue.poll();
            int now_x = poll.x;
            int now_y = poll.y;
            if (now_y == col - 1 && now_x == row - 1) {
                min = Integer.min(min, poll.depth);
            }
            for (int i = 0; i < 4; i++) {
                int nextX = now_x + MONKEY_STEP_X[i];
                int nextY = now_y + MONKEY_STEP_Y[i];
                if (nextX >= 0 && nextY >= 0 && nextY < col && nextX < row && map[nextX][nextY] != OBSTACLE
                        && !visitedStep[poll.horseStepCount][nextX][nextY]) {
                    visitedStep[poll.horseStepCount][nextX][nextY] = true;
                    queue.add(new Step(nextX, nextY, poll.horseStepCount, poll.depth + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                int nextX = now_x + HORSE_STEP_X[i];
                int nextY = now_y + HORSE_STEP_Y[i];
                if (nextX >= 0 && nextY >= 0 && nextY < col && nextX < row && poll.horseStepCount < k
                        && map[nextX][nextY] != OBSTACLE && !visitedStep[poll.horseStepCount + 1][nextX][nextY]) {
                    visitedStep[poll.horseStepCount + 1][nextX][nextY] = true;
                    queue.add(new Step(nextX, nextY, poll.horseStepCount + 1, poll.depth + 1));
                }
            }
        }


    }

    private static class Step {
        int x;
        int y;
        int horseStepCount;
        int depth;

        public Step(int x, int y, int horseStepCount, int depth) {
            this.x = x;
            this.y = y;
            this.horseStepCount = horseStepCount;
            this.depth = depth;
        }
    }

    private static void initMap(BufferedReader bf) throws IOException {
        k = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        visitedStep = new boolean[k + 1][row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }


}