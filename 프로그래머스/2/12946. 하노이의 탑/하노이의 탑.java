import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
	public int[][] solution(int n) {
    	hanoi(n, 1, 3, 2);
        return list.toArray(new int[list.size()][2]);
    }

	public void hanoi(int n, int from, int to, int mid){
    	if(n == 0) return;
        // 1. 시작위치에서 n-1개 기둥을 나머지 기둥으로 이동
        hanoi(n-1, from, mid, to);
        // 2. 맨 밑 기둥을 목표위치로 이동
        list.add(new int[]{from, to});
        // 3. 나머지 기둥으로 옮겨둔 n-1개 기둥을 다시 목표위치로 이동
        hanoi(n-1, mid, to, from);
    }
}