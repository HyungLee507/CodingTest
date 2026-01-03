import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] wordMask;
    static int baseMask;
    static int need;      
    static int best = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }
        if (K == 26) {
            System.out.println(N);
            return;
        }

        baseMask = 0;
        baseMask |= 1 << ('a' - 'a');
        baseMask |= 1 << ('n' - 'a');
        baseMask |= 1 << ('t' - 'a');
        baseMask |= 1 << ('i' - 'a');
        baseMask |= 1 << ('c' - 'a');

        wordMask = new int[N];

        for (int i = 0; i < N; i++) {
            String w = br.readLine().trim();

            String mid = w.substring(4, w.length() - 4);

            int m = 0;
            for (int j = 0; j < mid.length(); j++) {
                char ch = mid.charAt(j);
                m |= 1 << (ch - 'a');
            }
            wordMask[i] = m;
        }

        need = K - 5;

        dfs(0, 0, baseMask);

        System.out.println(best);
    }

    static void dfs(int start, int chosen, int teachMask) {
        if (chosen == need) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if ((wordMask[i] & teachMask) == wordMask[i]) cnt++;
            }
            if (cnt > best) best = cnt;
            return;
        }

        // 가지치기: 남은 알파벳 수로 필요한 개수 못 채우면 종료
        if (26 - start < need - chosen) return;

        for (int i = start; i < 26; i++) {
            int bit = 1 << i;

            if ((baseMask & bit) != 0) continue;

            dfs(i + 1, chosen + 1, teachMask | bit);
        }
    }
}
