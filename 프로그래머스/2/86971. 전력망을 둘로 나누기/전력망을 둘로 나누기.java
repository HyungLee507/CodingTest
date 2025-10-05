import java.util.*;

class Solution {
    int n;
    int[][] wires;
    List<Integer>[] adj;
    boolean[] visited;

    public int solution(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;

        // 1) 인접 리스트 구성
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] w : wires) {
            adj[w[0]].add(w[1]);
            adj[w[1]].add(w[0]);
        }

        // 2) 각 간선을 하나씩 끊어보며 차이 계산
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < wires.length; i++) {
            visited = new boolean[n + 1];
            // 끊는 간선의 한쪽 노드에서 시작해 컴포넌트 크기 계산
            int cnt = bfs(wires[i][0], i);
            answer = Math.min(answer, Math.abs(n - 2 * cnt));
        }
        return answer;
    }

    // skipIdx 번째 간선을 건너지 않도록 하면서 BFS
    private int bfs(int start, int skipIdx) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);

        int count = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adj[cur]) {
                // 끊은 간선(cur <-> nxt)이라면 스킵
                if ((cur == wires[skipIdx][0] && nxt == wires[skipIdx][1]) ||
                    (cur == wires[skipIdx][1] && nxt == wires[skipIdx][0])) {
                    continue;
                }
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    q.offer(nxt);
                    count++;
                }
            }
        }
        return count;
    }
}
