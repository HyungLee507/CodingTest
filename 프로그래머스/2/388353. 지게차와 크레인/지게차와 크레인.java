import java.util.*;

class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int solution(String[] storage, String[] requests) {
        int row = storage.length;
        int col = storage[0].length();
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            board[i] = storage[i].toCharArray();
        }

        int total = row * col;

        for (String request : requests) {
            int removedThisRequest = 0;

            if (request.length() == 1) {
                // ── 지게차 ──
                char target = request.charAt(0);

                // 1) 창고 외부와 연결된 빈칸만 BFS로 표시
                boolean[][] reachableBlank = new boolean[row][col];
                Queue<int[]> q = new LinkedList<>();

                // 경계에 있는 빈칸을 시작점으로
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if ((i == 0 || j == 0 || i == row - 1 || j == col - 1)
                                && board[i][j] == ' ') {
                            reachableBlank[i][j] = true;
                            q.offer(new int[]{i, j});
                        }
                    }
                }
                // BFS: 빈칸끼리만 연결 탐색
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int ni = cur[0] + dx[d];
                        int nj = cur[1] + dy[d];
                        if (ni < 0 || nj < 0 || ni >= row || nj >= col) continue;
                        if (board[ni][nj] == ' ' && !reachableBlank[ni][nj]) {
                            reachableBlank[ni][nj] = true;
                            q.offer(new int[]{ni, nj});
                        }
                    }
                }

                // 2) 실제 제거 대상 수집
                List<int[]> toRemove = new ArrayList<>();
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (board[i][j] != target) continue;

                        boolean canRemove = false;
                        // (1) 가장자리
                        if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                            canRemove = true;
                        }
                        // (2) 인접한 곳에 reachableBlank 있으면
                        for (int d = 0; d < 4 && !canRemove; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];
                            if (ni < 0 || nj < 0 || ni >= row || nj >= col) continue;
                            if (reachableBlank[ni][nj]) {
                                canRemove = true;
                            }
                        }
                        if (canRemove) {
                            toRemove.add(new int[]{i, j});
                        }
                    }
                }
                // 3) 한꺼번에 제거
                for (int[] p : toRemove) {
                    board[p[0]][p[1]] = ' ';
                    removedThisRequest++;
                }

            } else {
                // ── 크레인 ── (길이>1 → 해당 알파벳 전부 제거)
                char target = request.charAt(0);
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (board[i][j] == target) {
                            board[i][j] = ' ';
                            removedThisRequest++;
                        }
                    }
                }
            }

            total -= removedThisRequest;
        }

        return total;
    }
}
