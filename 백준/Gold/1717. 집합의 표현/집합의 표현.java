import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    public static final int ADD_SET = 0;
    public static final int IS_SAME_SET = 1;
    static int[] parents;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int sets = Integer.parseInt(st.nextToken());
        int executionCount = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, sets + 1).toArray();
        for (int i = 0; i < executionCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int executionNumber = Integer.parseInt(st.nextToken());
            int index1 = Integer.parseInt(st.nextToken());
            int index2 = Integer.parseInt(st.nextToken());
            execution(executionNumber, index1, index2);
        }
        System.out.println(sb);

    }

    private static void execution(int executionNumber, int index1, int index2) {
        if (executionNumber == ADD_SET) {
            if (unionFind(index1) != unionFind(index2)) {
                int num1 = unionFind(index1);
                int num2 = unionFind(index2);
                int max = Math.max(num1, num2);
                int min = Math.min(num1, num2);
                parents[max] = min;
            }
        }
        if (executionNumber == IS_SAME_SET) {
            if (unionFind(index1) == unionFind(index2)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
    }

    private static int unionFind(int index) {
        if (parents[index] == index) {
            return index;
        }
        return parents[index] = unionFind(parents[index]);
    }
}