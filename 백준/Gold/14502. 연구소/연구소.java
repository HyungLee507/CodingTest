import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int SAFE_ZONE = 0;
    public static final int WALL = 1;
    public static final int VIRUS = 2;

    static int[][] lab;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int totalSafeZone = 0;
    static int[][] virusLab;

    public static void main(String[] args) throws IOException {
        initData();
        search(0);
        System.out.println(totalSafeZone);
    }

    private static void search(int wallCount) {

        if (wallCount == 3) {
            spreadVirus();
            getMaxSafeZone();
            return;
        }

        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                if (lab[i][j] == SAFE_ZONE) {
                    lab[i][j] = WALL;
                    search(wallCount + 1);
                    lab[i][j] = SAFE_ZONE;
                }
            }
        }
    }

    private static void spreadVirus() {
        virusLab = new int[lab.length][lab[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < virusLab.length; i++) {
            for (int j = 0; j < virusLab[0].length; j++) {
                virusLab[i][j] = lab[i][j];
                if (virusLab[i][j] == VIRUS) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < virusLab.length && x + dx[i] >= 0 && y + dy[i] < virusLab[0].length && y + dy[i] >= 0
                        && virusLab[x + dx[i]][y + dy[i]] == SAFE_ZONE) {
                    virusLab[x + dx[i]][y + dy[i]] = VIRUS;
                    queue.add(new int[]{x + dx[i], y + dy[i]});
                }
            }
        }
    }

    private static void resetVirusMap() {
        for (int i = 0; i < lab.length; i++) {
            virusLab[i] = Arrays.copyOf(lab[i], lab[0].length);
        }
    }


    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        lab = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < col; j++) {
                int temp = Integer.parseInt(st.nextToken());
                lab[i][j] = temp;
            }
        }
    }

    private static void getMaxSafeZone() {
        int count = 0;
        for (int i = 0; i < virusLab.length; i++) {
            for (int j = 0; j < virusLab[0].length; j++) {
                if (virusLab[i][j] == SAFE_ZONE) {
                    count++;
                }
            }
        }
        totalSafeZone = Integer.max(count, totalSafeZone);
    }
}