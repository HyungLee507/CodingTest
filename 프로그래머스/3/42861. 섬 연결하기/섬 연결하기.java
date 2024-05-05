import java.util.*;
class Solution {
    int[] parent;
    
    private static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
    public int solution(int n, int[][] costs) {
        parent = new int[n+1];
        for(int i=0; i<=n; i++){
            parent[i] = i;
        }
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for(int i=0; i<costs.length;i++){
            queue.add(new Edge(costs[i][0],costs[i][1],costs[i][2]));
        }
        int totalEdge = 0;
        int totalCost = 0;
        while(totalEdge < n - 1){
            Edge edge = queue.poll();
            if(find(edge.start) != find(edge.end)){
                totalEdge++;
                totalCost += edge.cost;
                union(edge.start, edge.end);
            }
        }
        return totalCost;
    }
    private void union(int a ,int b){
        int x = find(a);
        int y = find(b);
        if(x != y){
            parent[y] = x; 
        }
    }
    
    private int find(int k){
        if(parent[k]==k){
            return k;
        }
        return find(parent[k]);
    }
}