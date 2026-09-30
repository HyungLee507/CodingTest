import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);

        int count = 0;

        for (int money : d) {
            if (budget < money) {
                break;
            }

            budget -= money;
            count++;
        }

        return count;
    }
}