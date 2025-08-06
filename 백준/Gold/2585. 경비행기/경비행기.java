
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int N, K;
    static Petition[] petitions;

    static class Petition {
        int x;
        int y;

        public Petition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        petitions = new Petition[N + 2];
        petitions[0] = new Petition(0, 0);
        petitions[N + 1] = new Petition(10000, 10000);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            petitions[i] = new Petition(x, y);
        }
        int left = -1;
        int right = 1001;
        int ans = 0;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (bfs(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        ans = right;
        System.out.println(ans);
    }

    static boolean bfs(int fuel) {
        int maxDistance = fuel * 10;
        visited = new boolean[N + 2];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0}); // index, stops
        visited[0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int idx = cur[0];
            int cnt = cur[1];

            if (idx == N + 1) {
                return cnt - 1 <= K;
            }

            for (int i = 0; i < N + 2; i++) {
                if (!visited[i] && i != idx && distance(petitions[idx], petitions[i]) <= maxDistance) {
                    visited[i] = true;
                    q.add(new int[]{i, cnt + 1});
                }
            }
        }
        return false;
    }

    static int distance(Petition a, Petition b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return (int) Math.ceil(Math.sqrt(dx * dx + dy * dy));
    }
}
