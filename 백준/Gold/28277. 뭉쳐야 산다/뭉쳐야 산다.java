import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static int[] I;
    static Map<Integer, Set<Integer>> S = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        I = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            I[i] = i;
            S.put(i, new HashSet<>());
            st = new StringTokenizer(r.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                S.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(r.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            if (t == 1) {
                int b = Integer.parseInt(st.nextToken());
                if (S.get(I[a]).size() < S.get(I[b]).size()) {
                    int temp = I[a];
                    I[a] = I[b];
                    I[b] = temp;
                }
                S.get(I[a]).addAll(S.get(I[b]));
                S.get(I[b]).clear();
            } else {
                sb.append(S.get(I[a]).size()).append('\n');
            }
        }

        System.out.print(sb);
    }
}
