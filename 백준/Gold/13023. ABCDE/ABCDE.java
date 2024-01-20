import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    static List<Integer>[] adjustNodes;
    static boolean[] isVisited;

    static int haveRelationship = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int vertexes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());

        initSetting(vertexes);

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            adjustNodes[first].add(second);
            adjustNodes[second].add(first);
        }
        int startNodeIndex = 0;
        while (haveRelationship == 0 && startNodeIndex < vertexes) {
            dfs(startNodeIndex, 1);
            //todo : 여기에 isVisited 클리어하고
            startNodeIndex++;
            isVisited = new boolean[vertexes];
        }
        System.out.println(haveRelationship);

    }

    private static void initSetting(int vertexes) {
        isVisited = new boolean[vertexes];
        adjustNodes = new ArrayList[vertexes];
        for (int i = 0; i < vertexes; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
    }

    private static void dfs(int startNode, int depth) {
        isVisited[startNode] = true;
        if (depth == 5) {
            haveRelationship = 1;
            return;
        }

        for (Integer i : adjustNodes[startNode]) {
            if (!isVisited[i]) {
                dfs(i, depth + 1);
            }
        }
        isVisited[startNode] = false;

    }

}