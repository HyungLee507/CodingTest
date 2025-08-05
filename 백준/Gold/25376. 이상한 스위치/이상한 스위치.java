import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, initState;
    static int[] effects;

    static class State {
        int state;
        int count;

        State(int state, int count) {
            this.state = state;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(bf.readLine());
        initState = 0;

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(st.nextToken());
            if (s == 1) {
                initState |= (1 << i);
            }
        }

        effects = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int mask = 0;
            for (int j = 0; j < cnt; j++) {
                int target = Integer.parseInt(st.nextToken()) - 1;
                mask |= (1 << target);
            }
            effects[i] = mask;
        }

        if (initState == (1 << N) - 1) {
            System.out.println(0);
        } else {
            System.out.println(bfs());
        }
    }

    static int bfs() {
        Queue<State> q = new LinkedList<>();
        boolean[] visited = new boolean[1 << N];

        q.offer(new State(initState, 0));
        visited[initState] = true;

        int goal = (1 << N) - 1;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.state == goal) {
                return cur.count;
            }

            for (int i = 0; i < N; i++) {
                if ((cur.state & (1 << i)) == 0) {
                    int next = cur.state;
                    next |= (1 << i);
                    next ^= effects[i];

                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(new State(next, cur.count + 1));
                    }
                }
            }
        }
        return -1;
    }


}
