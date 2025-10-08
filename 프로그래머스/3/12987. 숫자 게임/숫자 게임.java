import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0;      // A의 포인터 (가장 작은 것부터)
        int j = 0;      // B의 포인터 (가장 작은 것부터)
        int wins = 0;   // B의 승점

        while (i < A.length && j < B.length) {
            if (B[j] > A[i]) {
                wins++;
                i++;
                j++;
            } else {           
                j++;
            }
        }
        return wins;
    }
}
