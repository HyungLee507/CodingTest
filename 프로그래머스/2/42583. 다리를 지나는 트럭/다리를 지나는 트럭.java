import java.util.*;
class Solution {
    
    private static class Truck{
        int time;
        int weight;
        public void addTime(){
            this.time++;
        }
        
        public Truck(int time, int weight){
            this.time = time;
            this.weight = weight;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        int passed = 0;
        int next = 1;
        int totalWeight = 0;
        Deque<Truck> bridge = new ArrayDeque<>();
        // Arrays.sort(truck_weights);
        totalWeight = truck_weights[0];
        bridge.add(new Truck(1,truck_weights[0]));
        while(passed < truck_weights.length){
            answer++;
            for(Truck truck : bridge){
                truck.addTime();
                if(truck.time > bridge_length){
                    bridge.poll();
                    passed++;
                    totalWeight -= truck.weight;
                }
            }
            if(next < truck_weights.length && totalWeight + truck_weights[next] <= weight){
                bridge.add(new Truck(1,truck_weights[next]));
                totalWeight += truck_weights[next];
                next++;
            }   
        }
        return answer;
    }
}