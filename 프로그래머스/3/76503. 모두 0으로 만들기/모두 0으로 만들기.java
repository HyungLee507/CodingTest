import java.util.*;
class Solution {
    
        static List<Node>[] adjustNodes;
        static boolean[] visited;
        long[] array;
    
    private static class Node{
        
        int index; 
        public Node(int index){
            this.index = index;
        }
    }
    
    public long solution(int[] a, int[][] edges) {
        if(Arrays.stream(a).sum()!=0) {
            return -1L;
        }
        init(a, edges);
        long[] total = dfs(0,a);
        return total[0];
    }
    
    private long[] dfs(int index, int[] a){
        visited[index] = true;
        List<Node> adjusts = adjustNodes[index];
        long count = 0;
        long currentValue = a[index];
        for (int i = 0; i < adjusts.size(); i++) {
            Node node = adjusts.get(i);
            if (!visited[node.index]) {
                long[] temp = dfs(node.index, a);
                count += Math.abs(temp[0]);
                currentValue += temp[1];
            }
        }
        return new long[]{count + Math.abs(currentValue), currentValue};
    }
    
    private void init(int[] a, int[][] edges){
        adjustNodes = new List[a.length];
        visited = new boolean[a.length];
        array = new long[a.length];
        for (int i = 0; i < a.length; i ++) {
            array[i] = (long) a[i];
        }
        for (int i=0; i<a.length; i++){
            adjustNodes[i] = new ArrayList<>();
            
        }
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            adjustNodes[node1].add(new Node(node2));
            adjustNodes[node2].add(new Node(node1));
        }
    }
    
    
}