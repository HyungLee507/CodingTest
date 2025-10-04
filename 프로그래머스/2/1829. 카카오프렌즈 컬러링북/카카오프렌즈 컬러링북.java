import java.util.*;
class Solution {
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    boolean[][] visited;
    int M, N;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        M = m;
        N = n;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(picture[i][j]!=0 && !visited[i][j]){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea,bfs(i,j,picture[i][j],picture));
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int x, int y, int value, int[][] picture){
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        int cnt =0;
        while(!q.isEmpty()){
            int[] now = q.poll();
            cnt++;
            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx < 0 || nx >= M ||ny < 0 || ny >= N){
                    continue;
                }
                if(picture[nx][ny] == value && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }
        }
        return cnt;
    }
    
    
    
}