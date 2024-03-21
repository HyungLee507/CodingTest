import java.util.*;
class Solution {
    
    boolean[][] map;
    boolean[][] visited;
    int start_X;
    int start_Y;
    int lever_X;
    int lever_Y;
    int exit_X;
    int exit_Y;
    int count = 0;
    int[] dir1 = {1,-1,0,0};    
    int[] dir2 = {0,0,1,-1};
    
    public int solution(String[] maps) {
        int answer = 0;
        map = new boolean[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        initMap(maps);
        
        if(findLever()){
            visited = new boolean[maps.length][maps[0].length()];
            answer = findExit();
        }else{
            return -1;
        }
        return answer;
    }
    
    public  boolean findLever() {
        Queue<Location> deque = new LinkedList<>();
        deque.add(new Location(start_X, start_Y, 0));
        while (!deque.isEmpty()) {
            Location now = deque.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowCount = now.count;
            visited[nowX][nowY] = true;
            if (nowX == lever_X && nowY == lever_Y) {
                count = nowCount;
                return true;
            }
            for (int i = 0; i < 4; i++) {
                if (nowX + dir1[i] >= 0 && nowY + dir2[i] >= 0 && nowX + dir1[i] < map.length
                        && nowY + dir2[i] < map[0].length && !visited[nowX + dir1[i]][nowY + dir2[i]]
                        && !map[nowX + dir1[i]][nowY + dir2[i]]) {
                    visited[nowX + dir1[i]][nowY + dir2[i]] = true;
                    deque.add(new Location(nowX + dir1[i], nowY + dir2[i], nowCount + 1));
                }
            }
        }
        return false;
    }
    public int findExit() {
        boolean isFind = false;
        Queue<Location> deque = new LinkedList<>();
        deque.add(new Location(lever_X, lever_Y, count));
        while (!deque.isEmpty()) {
            Location now = deque.poll();
            int nowX = now.x;
            int nowY = now.y;
            visited[nowX][nowY] = true;
            int nowCount = now.count;
            if (nowX == exit_X && nowY == exit_Y) {
                return nowCount;
            }
            for (int i = 0; i < 4; i++) {
                if (nowX + dir1[i] >= 0 && nowY + dir2[i] >= 0 && nowX + dir1[i] < map.length
                        && nowY + dir2[i] < map[0].length && !visited[nowX + dir1[i]][nowY + dir2[i]]
                        && !map[nowX + dir1[i]][nowY + dir2[i]]) {
                    visited[nowX + dir1[i]][nowY + dir2[i]] = true;
                    deque.add(new Location(nowX + dir1[i], nowY + dir2[i], nowCount + 1));
                }
            }
        }
        return -1;


    }
    
    
    public void initMap(String[] maps) {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'O') {
                    map[i][j] = false;
                }
                if (maps[i].charAt(j) == 'X') {
                    map[i][j] = true;
                }
                if (maps[i].charAt(j) == 'E') {
                    exit_X = i;
                    exit_Y = j;
                }
                if (maps[i].charAt(j) == 'L') {
                    lever_X = i;
                    lever_Y = j;
                }
                if (maps[i].charAt(j) == 'S') {
                    start_X = i;
                    start_Y = j;
                }
            }
        }
    }
    static class Location{
        int x;
        int y;
        int count;
        public Location(int x, int y,int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}