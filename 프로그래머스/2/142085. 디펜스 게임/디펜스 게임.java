import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int step = k;
        if(step >= enemy.length){
            return enemy.length;
        }
        for(int i=0; i<k;i++){
            queue.add(enemy[i]);
        }
        while (true) {
            if (step >= enemy.length) {
                break;
            }
            int min = queue.peek();
            if (min <= enemy[step] && n - min >= 0) {
                n -= (queue.poll());
                queue.add(enemy[step]);
                step++;
                continue;
            } else if (min <= enemy[step] && n - min < 0) {
                break;
            }
            if (min > enemy[step] && n - enemy[step] >= 0) {
                n -= enemy[step];
                step++;
            } else if (min > enemy[step] && n - enemy[step] < 0) {
                break;
            }
        }
        return step;
    }
}