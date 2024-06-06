import java.util.*;
class Solution {
    int number;
    List<Integer>[] map;
    boolean[] visited ;
    private static class Route{
        int cityNumber;
        long cost;
        public Route(int cityNumber, long cost){
            this.cityNumber = cityNumber;
            this.cost = cost;
        }
    }
    
    long[] distance;
    public int[] solution(int n, int[][] roads, int[] sources, int destination){
        int[] answer = new int[sources.length];
        map = new ArrayList[n+1];
        visited = new boolean[n+1];
        distance = new long[n + 1];

        for(int i=1; i<=n; i++){
            map[i] = new ArrayList<>();
        }
        for(int[] road : roads){
            int start = road[0];
            int end = road[1];
            map[start].add(end);
            map[end].add(start);
        }
        
        dks(destination);
        for (int i=0; i<answer.length; i++){
            if((int)distance[sources[i]] == Integer.MAX_VALUE){
                answer[i] = -1;    
            }else{
                answer[i] = (int) distance[sources[i]];            
            }
        }
        return answer;
    }
    private void dks(int destination) {
        PriorityQueue<Route> pq = new PriorityQueue<>((r1, r2) -> Long.compare(r1.cost, r2.cost));

        pq.add(new Route(destination, 0));
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[destination] = 0;
        
        while (!pq.isEmpty()){
            Route now = pq.poll();
            if(!visited[now.cityNumber]){
                visited[now.cityNumber] = true;
                for(int next : map[now.cityNumber]){
                   if(!visited[next] && distance[next] > distance[now.cityNumber] + 1){
                       distance[next] = distance[now.cityNumber] + 1;
                       pq.add(new Route(next,distance[next]));
                   } 
                }
            }
                
            }
            
        }
    
    
}