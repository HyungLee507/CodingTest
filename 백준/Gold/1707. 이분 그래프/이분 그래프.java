import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] adjustNodes;
    static int[] visited;

    static int[] depths;

    public static final int BLUE = 1;
    public static final int RED = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int iterations = Integer.parseInt(bf.readLine());
        for (int i = 0; i < iterations; i++) {
            st = new StringTokenizer(bf.readLine());
            int nodes = Integer.parseInt(st.nextToken());
            int edges = Integer.parseInt(st.nextToken());

            adjustNodes = new ArrayList[nodes + 1];
            visited = new int[nodes + 1];

            for (int j = 1; j <= nodes; j++) {
                adjustNodes[j] = new ArrayList<>();
            }

            for (int j = 0; j < edges; j++) {
                st = new StringTokenizer(bf.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                adjustNodes[num1].add(num2);
                adjustNodes[num2].add(num1);
            }
            boolean bipartiteGraph = true;
            for (int j = 1; j < nodes; j++) {
                if (!isBipartiteGraph(j)) {
                    bipartiteGraph = false;
                    break;
                }
            }
            if (bipartiteGraph) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }


    }

    private static boolean isBipartiteGraph(int searchIndex) {

        if (visited[searchIndex] != 0) {
            return true;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(searchIndex);
        visited[searchIndex] = BLUE;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            int currentColor = visited[poll];
            for (Integer i : adjustNodes[poll]) {
                if (visited[i] == 0) {
                    visited[i] = currentColor * -1;
                    queue.add(i);
                } else if (visited[i] == currentColor) {
                    return false;
                }
            }
        }
        return true;
    }


}