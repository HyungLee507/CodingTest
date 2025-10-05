import java.util.*;

class Solution {
    int answer;
    int maxDepth;
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        answer = 0;
        maxDepth = dungeons.length; 
        visited = new boolean[maxDepth];
        dfs(k, dungeons, 0);
        return answer;
    }

    public void dfs(int mp, int[][] dungeons, int cnt){
        boolean canVisit = false;

        for(int i=0; i<maxDepth; i++){
            int minPiro = dungeons[i][0];
            int usePiro = dungeons[i][1];
            if(!visited[i] && minPiro <= mp){
                canVisit = true;
                visited[i] = true;
                dfs(mp - usePiro, dungeons, cnt + 1);
                visited[i] = false;
            }
        }

        if(!canVisit){
            answer = Math.max(answer, cnt);
        }
    }
}
