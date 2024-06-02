import java.util.*;
class Solution {
    int min;
    public int solution(int[] stones, int k) {
        int n = stones.length;
        Deque<Integer> deque = new LinkedList<>();
        int maxCrossings = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            // Deque에서 윈도우 범위를 벗어나는 인덱스 제거
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // 현재 값보다 작은 값을 덱의 뒤에서부터 제거
            while (!deque.isEmpty() && stones[deque.peekLast()] <= stones[i]) {
                deque.pollLast();
            }
            
            // 현재 인덱스를 덱에 추가
            deque.offerLast(i);
            
            // i가 k-1 이상일 때만 윈도우의 최대 값 갱신
            if (i >= k - 1) {
                maxCrossings = Math.min(maxCrossings, stones[deque.peekFirst()]);
            }
        }
        return maxCrossings;
    }
}