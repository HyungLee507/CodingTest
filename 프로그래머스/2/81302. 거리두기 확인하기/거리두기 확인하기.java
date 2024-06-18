import java.util.*;
class Solution {
    int[] dirX = {1,-1,0,0};
    int[] dirY = {0, 0,1,-1};
    private static class Location{
        int x; 
        int y;
        int count;
        public Location(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++){
            if(isDivided(places[i])){
                answer[i] = 1;
            }
        }
        return answer;
    }
    private boolean isDivided(String[] place){
        List<int[]> locations = new ArrayList<>();
        for(int i=0; i<place.length; i++){
            for(int j=0; j<place[i].length(); j++){
                if(place[i].charAt(j)=='P'){
                    locations.add(new int[]{i,j});
                }
            }
        }
        for(int[] location : locations){
            if(canReach(location, place)){
                return false;
            }
        }
        return true;
    }
    private boolean canReach(int[] location, String[] place) {
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        queue.add(new Location(location[0], location[1], 0));
        visited[location[0]][location[1]] = true;
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            // 만약 이게 시작 지점이 아니고 다른 대기자라면 return true
            if ((now.x != location[0] || now.y != location[1]) && place[now.x].charAt(now.y) == 'P') {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = dirX[i] + now.x;
                int nextY = dirY[i] + now.y;
                if (nextX >= 0 && nextY >= 0 && nextX < 5 && nextY < 5 && !visited[nextX][nextY] && now.count <= 1
                        && place[nextX].charAt(nextY) != 'X') {
                    visited[nextX][nextY] = true;
                    queue.add(new Location(nextX, nextY, now.count + 1));
                }
            }
        }
        return false;
    }
}