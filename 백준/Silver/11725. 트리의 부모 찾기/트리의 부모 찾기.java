
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static boolean[] visited;
    static List<Integer>[] result;
    static int[] answer;

    private static void dfs(int x) {
        visited[x] = true;

        for (int node : result[x]) {
            if(!visited[node]) {
                answer[node - 2] = x;
                dfs(node);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        answer = new int[N];
        result = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            result[i] = new ArrayList<>();
        }

        for (int j = 1; j < N; j++) {
            st = new StringTokenizer(br.readLine());
            int x, y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            result[x].add(y);
            result[y].add(x);
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - 1; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.println(sb);
    }
}