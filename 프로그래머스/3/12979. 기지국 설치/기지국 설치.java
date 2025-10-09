import java.util.*;

class Solution {
    public int solution(int N, int[] stations, int W) {
        long cover = 2L * W + 1;   
        long need = 0L;
        int prev = 0;              

        Arrays.sort(stations);

        for (int s : stations) {
            int L = Math.max(1, s - W);
            int R = Math.min(N, s + W);

            int start = prev + 1;
            int end = L - 1;
            if (start <= end) {
                long gapLen = (long) end - start + 1;
                need += (gapLen + cover - 1) / cover; 
            }

            
            prev = Math.max(prev, R);
            if (prev >= N) break; 
        }

        if (prev < N) {
            long gapLen = (long) N - (prev + 1) + 1; 
            need += (gapLen + cover - 1) / cover;
        }

        return (int) need;
    }
}
