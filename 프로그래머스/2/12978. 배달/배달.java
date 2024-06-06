import java.util.*;
class Solution {
    private static class Node implements Comparable<Node>{
        int idx;
        int cost;
        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node n){
            return Integer.compare(this.cost, n.cost);
        }
    }
    boolean[] visited;
    long[] dist;
    List<Node>[] adjustNodes;
    
    public int solution(int N, int[][] road, int K) {
        adjustNodes = new ArrayList[N+1];
        dist = new long[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        dist[1] = 0;
        for(int i=1; i<=N; i++){
            adjustNodes[i] = new ArrayList<>();
        }
        for(int i=0; i< road.length; i++){
            int start = road[i][0];
            int end = road[i][1];
            int cost = road[i][2];
            adjustNodes[start].add(new Node(end,cost));
            adjustNodes[end].add(new Node(start,cost));
        }
        dks(1);
        int answer = 1;
        for(int i=2; i<= N; i++){
            if(dist[i] <= K){
                answer++;
            }
        }
        return answer;
    }
    private void dks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(visited[now.idx]){
                continue;
            }
            visited[now.idx] = true;
            for(Node next : adjustNodes[now.idx]){
                long nextCost = next.cost + dist[now.idx];
                if(nextCost < dist[next.idx]){
                    dist[next.idx] = nextCost;
                    pq.add(new Node(next.idx, (int)nextCost));
                }
            }
        }
        
    }
}