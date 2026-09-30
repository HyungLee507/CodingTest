import java.util.*;
class Solution {
    List<Integer>[] adjustNodes;
    public int solution(int n, int[][] edge) {
        adjustNodes = new List[n+1];
        for(int i=0; i<n+1; i++) adjustNodes[i] = new ArrayList<>();
        for(int[] e : edge){
            int start = e[0];
            int end = e[1];
            adjustNodes[start].add(end);
            adjustNodes[end].add(start);
        }
        return bfs(1,n);
    }
    
    public int bfs(int start, int num){
        int res = 0;
        int maxDepth = -1;
        boolean[] visited = new boolean[num+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start,0});
        visited[start] = true;
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int now = poll[0];
            int depth = poll[1];
            for(int next : adjustNodes[now]){
                if(!visited[next]){
                    visited[next] = true;
                     q.offer(new int[]{next, depth+1});
                    if(maxDepth < depth+1){
                        maxDepth = depth+1;
                        res = 1;
                        continue;
                    }
                    if(maxDepth == depth+1){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}