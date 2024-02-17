import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] degree;
    static List<Integer>[] rules;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int studentCount = Integer.parseInt(st.nextToken());
        int comparedHeightCount = Integer.parseInt(st.nextToken());

        degree = new int[studentCount + 1];
        rules = new List[studentCount + 1];

        for (int i = 0; i <= studentCount; i++) {
            rules[i] = new ArrayList<>();
        }

        for (int i = 0; i < comparedHeightCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            degree[big]++;
            rules[small].add(big);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= studentCount; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Integer peek = queue.poll();
            sb.append(peek).append(" ");
            for (Integer i : rules[peek]) {
                degree[i]--;
                if (degree[i] == 0) {
                    queue.add(i);
                }
            }
        }
        System.out.println(sb);

    }


}