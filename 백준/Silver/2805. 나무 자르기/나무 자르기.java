import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            trees[i] = h;
            if (h > max) max = h;
        }

        long lo = 0, hi = max, ans = 0;
        while (lo <= hi) {
            long mid = (lo + hi) >>> 1; 
            long got = 0;

            for (int h : trees) {
                if (h > mid) got += (h - mid);
            }

            if (got >= M) {      
                ans = mid;
                lo = mid + 1;
            } else {             
                hi = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
