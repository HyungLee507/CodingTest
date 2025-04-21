class Solution {
    int n, m;
    int[][] q;
    int[] ans;
    int[] cnt; 

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        this.m = q.length;
        this.cnt = new int[m];
        return dfs(1, 0);
    }

    private int dfs(int start, int depth) {
        if (depth == 5) {
            for (int i = 0; i < m; i++) {
                if (cnt[i] != ans[i]) return 0;
            }
            return 1;
        }

        int total = 0;
        if (n - start + 1 < 5 - depth) return 0;

        for (int x = start; x <= n; x++) {
            boolean prune = false;
            for (int i = 0; i < m; i++) {
                for (int v : q[i]) {
                    if (v == x) {
                        cnt[i]++;
                        break;
                    }
                }
                if (cnt[i] > ans[i]) {
                    prune = true;
                }
            }
            if (!prune) {
                total += dfs(x + 1, depth + 1);
            }
            for (int i = 0; i < m; i++) {
                for (int v : q[i]) {
                    if (v == x) {
                        cnt[i]--;
                        break;
                    }
                }
            }
        }
        return total;
    }
}
