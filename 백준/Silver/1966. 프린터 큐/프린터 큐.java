import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] cnt = new int[10];         
            Deque<int[]> q = new ArrayDeque<>(); 

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int p = Integer.parseInt(st.nextToken());
                cnt[p]++;
                q.add(new int[]{i, p});
            }

            int top = 9; 
            while (top > 0 && cnt[top] == 0) top--;

            int order = 0;
            while (true) {
                int[] cur = q.pollFirst();
                int idx = cur[0], pri = cur[1];

                if (pri == top) { 
                    order++;
                    cnt[pri]--;
                    if (idx == M) {
                        sb.append(order).append('\n');
                        break;
                    }
                    while (top > 0 && cnt[top] == 0) top--;
                } else {
                    q.addLast(cur);
                }
            }
        }

        System.out.print(sb.toString());
    }
}
