import java.util.*;
class Solution {
    boolean[] visited;
    Set<Integer>[] adjustNodes;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        adjustNodes = new HashSet[n];
        for (int i = 0; i < n; i++) {
            adjustNodes[i] = new HashSet<>();
        }
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if (computers[i][j] == 1) {
                    adjustNodes[i].add(j);
                }
            }

        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            } else {
                continue;
            }
            if (checkAllCity()) {
                break;
            }
        }
        return answer;
    }
    
    private void dfs(int node){
        visited[node] = true;
        Set<Integer> adjust = adjustNodes[node];
        for(int nextNode : adjust){
            if(!visited[nextNode]){
                dfs(nextNode);
            }
        }
        
    }
    private boolean checkAllCity(){
        for(int i=0; i<visited.length;i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
}