import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int size = queue1.length;
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        long totalSum = sum1 + sum2;
        int index1 = 0;
        int index2 = 0;
        while (sum1 != sum2 && answer < 3 * size) {
            if (sum1 > totalSum / 2 && index1 < size) {
                sum2 += queue1[(index1)];
                sum1 -= queue1[(index1)];
                index1++;
                answer++;
            } else if (sum1 > totalSum / 2 && index1 >= size) {
                sum2 += queue2[(index1 % size)];
                sum1 -= queue2[(index1 % size)];
                index1++;
                answer++;
            } else if (sum2 > totalSum / 2 && index2 < size) {
                sum1 += queue2[(index2)];
                sum2 -= queue2[(index2)];
                index2++;
                answer++;
            } else if (sum2 > totalSum / 2 && index2 >= size) {
                sum1 += queue1[(index2 % size)];
                sum2 -= queue1[(index2 % size)];
                index2++;
                answer++;
            }
        }
        if (answer >= 3 * size) {
            answer = -1;
        }
        return answer;
    }
}