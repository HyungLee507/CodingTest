import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> counts = new ArrayList<>();
        for(int i=0; i<speeds.length;i++){
            int temp = (100 - progresses[i]) % speeds[i] ==0 ? (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] +1;
            if(queue.isEmpty()){
                queue.offer(temp);
                counts.add(1);
            }else{
                int before = queue.peek();
                if(before >= temp){
                    counts.set(counts.size()-1, counts.get(counts.size()-1)+1);
                }else{
                    queue.poll();
                    queue.add(temp);
                    counts.add(1);
                }
            }
        }
        int[] ans = new int[counts.size()];
        for(int i=0; i<counts.size(); i++){
            ans[i] = counts.get(i);
        }
        return ans;
    }
}