import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        int answer = 0;        
        Queue<String> queue = new LinkedList<>();
        for(String city : cities){
            String cityLower = city.toLowerCase();
           if(queue.contains(cityLower)){
               answer++;
               queue.remove(cityLower);
               queue.offer(cityLower);
               continue;
           }
            if(queue.size() == cacheSize){
                queue.poll();    
            }
            queue.offer(cityLower);
            answer+=5;            
        }
        return answer;
    }
}