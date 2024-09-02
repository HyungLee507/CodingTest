
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static final int[] dirX = {0, 0, 1, -1};
    public static final int[] dirY = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static List<Core> cores;

    static int totalCore;
    static int minLines;

    private static class Core {
        int x;
        int y;
        List<Integer> direction;

        public Core(int y, int x) {
            this.y = y;
            this.x = x;
            this.direction = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(bf.readLine());
        StringTokenizer st;

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            minLines = 0;
            totalCore = 0;
            cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1 && (i != 0 || i != N - 1 || j != 0 || j != N - 1)) {
                        cores.add(new Core(i, j));
                        visited[i][j] = true;
                    }
                }
            }
            dfs(0, 0, 0);
            System.out.printf("#%d %d\n", test_case, minLines);
        }
    }

    //    private void dfs(int index, int count, int lineLength) {
//        // cores 에 있는 놈들을 순차적으로 순회하면서 상하좌우 보면서 count 값과 lineLength 를 최신화.
//        // 중단조건은 index 값이 cores의 size() 랑 같아지면
//        // 근데 이때 count 값이 더 크면 lineLength update, 만약 count 같이 같다면 lineLength를 update.
//        if (index == cores.size()) {
//            if (count > totalCore) {
//                totalCore = count;
//                minLines = lineLength;
//                return;
//            }
//            if (count == totalCore) {
//                minLines = Math.min(minLines, lineLength);
//            }
//            return;
//        }
//        // 여기가 사방탐색 하면서 사방탐색 조건 만족시 dfs 타고 들어가고 빠져나오면 visited 원상복구해야됨.
//        Core core = cores.get(index);
//        for (int i = 0; i < 4; i++) {
//
//        }
//        dfs(index + 1, count, lineLength);
//    }
    private static void dfs(int index, int count, int lineLength) {
        // 남은 코어 수가 현재 최적의 코어 수보다 작다면 더 이상 탐색할 필요가 없음
		if (count + (cores.size() - index) < totalCore) {
            return;
        }
        // 모든 코어에 대해 탐색이 끝난 경우

        if (index == cores.size()) {
            if (count > totalCore) {
                totalCore = count;
                minLines = lineLength;
            } else if (count == totalCore) {
                minLines = Math.min(minLines, lineLength);
            }
            return;
        }

        Core core = cores.get(index);

        boolean isConnected = false;
        for (int i = 0; i < 4; i++) {
            int newY = core.y;
            int newX = core.x;
            int tempLength = 0;

            // 현재 방향으로 끝까지 전선을 설치할 수 있는지 체크
            while (true) {
                newY += dirY[i];
                newX += dirX[i];

                // 맵을 벗어나면 연결 가능
                if (newY < 0 || newY >= N || newX < 0 || newX >= N) {
                    isConnected = true;
                    break;
                }

                // 다른 코어 또는 전선이 있으면 연결 불가
                if (map[newY][newX] != 0) {
                    isConnected = false;
                    break;
                }

                tempLength++;
            }

            // 연결 가능하면 전선 설치
            if (isConnected) {
                newY = core.y;
                newX = core.x;

                while (true) {
                    newY += dirY[i];
                    newX += dirX[i];

                    if (newY < 0 || newY >= N || newX < 0 || newX >= N) {
                        break;
                    }

                    map[newY][newX] = 2; // 전선 표시
                }

                // 다음 코어로 DFS 진행
                dfs(index + 1, count + 1, lineLength + tempLength);

                // 백트래킹: 전선 제거
                newY = core.y;
                newX = core.x;

                while (true) {
                    newY += dirY[i];
                    newX += dirX[i];

                    if (newY < 0 || newY >= N || newX < 0 || newX >= N) {
                        break;
                    }

                    map[newY][newX] = 0; // 전선 제거
                }
            }
        }

        // 현재 코어를 연결하지 않고 다음 코어로 진행
        dfs(index + 1, count, lineLength);
    }
}

