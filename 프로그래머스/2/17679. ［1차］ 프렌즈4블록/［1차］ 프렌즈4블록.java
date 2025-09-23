import java.util.*;

class Solution {
    int[][] map;   
    int M, N;

    public int solution(int m, int n, String[] board) {
        this.M = m; this.N = n;
        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                map[i][j] = (c == '.' ? -1 : (c - 'A'));
            }
        }

        int answer = 0;
        while (true) {
            // TODO: 순회 돌면서 q를 채워줌.
            LinkedList<int[]> q = getRemoveList();
            if (q.isEmpty()) break;

            answer += q.size();
            // TODO: map 아래로 밀어내기
            downBlock(q);
        }
        return answer;
    }

    public LinkedList<int[]> getRemoveList() {
        boolean[][] remove = new boolean[M][N];
        int marked = 0;

        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (isSquare(i, j)) {
                    for (int x = i; x < i + 2; x++) {
                        for (int y = j; y < j + 2; y++) {
                            if (!remove[x][y]) {
                                remove[x][y] = true;
                                marked++;
                            }
                        }
                    }
                }
            }
        }

        LinkedList<int[]> q = new LinkedList<>();
        if (marked == 0) return q;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (remove[i][j]) q.add(new int[]{i, j});
            }
        }
        return q;
    }

    private boolean isSquare(int i, int j) {
        int v = map[i][j];
        if (v == -1) return false;
        return map[i][j + 1] == v && map[i + 1][j] == v && map[i + 1][j + 1] == v;
    }

    public void downBlock(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int[] p = q.poll();
            map[p[0]][p[1]] = -1;
        }

        for (int col = 0; col < N; col++) {
            int write = M - 1;                 
            for (int row = M - 1; row >= 0; row--) {
                if (map[row][col] != -1) {
                    int v = map[row][col];
                    if (write != row) {
                        map[write][col] = v;
                        map[row][col] = -1;
                    }
                    write--;
                }
            }
        }
    }
}
