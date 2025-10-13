import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static boolean[] visited;
    static int[] before;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[200_001];
        before = new int[200_001];
        bfs();
        Deque<Integer> stack = new ArrayDeque<>();
        int value = K;
        while (value != N) {
            stack.push(value);
            value = before[value];
        }
        stack.push(N);
        int total = stack.size();
        System.out.println(total - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < total; i++) {
            sb.append(stack.pop()).append(' ');
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{N, -1});
        visited[N] = true;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int now = poll[0];
            int post = poll[1];
            if (now == K) {
                before[K] = post;
                return;
            }
            int[] next = new int[3];
            next[0] = poll[0] + 1;
            next[1] = poll[0] - 1;
            next[2] = poll[0] * 2;
            for (int i = 0; i < 3; i++) {
                int nx = next[i];
                if (nx >= 0 && nx <= 200_000 && !visited[nx]) {
                    visited[nx] = true;
                    q.offer(new int[]{nx, now});
                    before[nx] = now;
                }
            }
        }
    }
}
