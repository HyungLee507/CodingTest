import java.util.*;
class Solution {
    
    public int[] solution(String[] operations) {
        
        TreeSet<Integer> pq = new TreeSet<>();
        for(String operation : operations){
            String[] order = operation.split(" ");
            if(order[0].equals("I")){
                pq.add(Integer.parseInt(order[1]));
            }
            if(order[0].equals("D") && order[1].equals("1")){
                pq.pollLast();
            }
            if(order[0].equals("D") && order[1].equals("-1")){
                pq.pollFirst();
            }
        }
            
        int[] answer = new int[2];
        if(pq.isEmpty()){
            return answer;
        }
        answer[1] = pq.first();
        answer[0] = pq.last();       
        return answer;
    }
}