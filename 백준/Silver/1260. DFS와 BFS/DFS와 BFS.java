import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    static boolean[] isVisited;
    static List<Integer>[] adjustNodes;
    static StringBuilder sb = new StringBuilder();

    static Queue<Integer> bfsQueue = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int vertexes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int startIndex = Integer.parseInt(st.nextToken());

        isVisited = new boolean[vertexes + 1];
        adjustNodes = new ArrayList[vertexes + 1];
        for (int i = 1; i <= vertexes; i++) {
            adjustNodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adjustNodes[node1].add(node2);
            adjustNodes[node2].add(node1);
        }

        for (int i = 1; i <= vertexes; i++) {
            adjustNodes[i].sort(Comparator.naturalOrder());
        }
        dfs(startIndex);
        sb.append('\n');
        for (int i = 1; i <= vertexes; i++) {
            isVisited[i] = false;
        }
        bfsQueue.add(startIndex);
        bfs(startIndex);
        System.out.println(sb);
    }

    private static void dfs(int startNumber) {

        isVisited[startNumber] = true;
        sb.append(startNumber);
        sb.append(" ");
        for (int next : adjustNodes[startNumber]) {
            if (!isVisited[next]) {
                dfs(next);
            }
        }
    }

    private static void bfs(int startNumber) {
        isVisited[startNumber] = true;
        for (int next : adjustNodes[startNumber]) {
            if (!isVisited[next] && !bfsQueue.contains(next)) {
                bfsQueue.add(next);
            }
        }
        bfsQueue.poll();
        sb.append(startNumber);
        sb.append(" ");
        if (!bfsQueue.isEmpty()) {
            bfs(bfsQueue.peek());
        }
    }

}