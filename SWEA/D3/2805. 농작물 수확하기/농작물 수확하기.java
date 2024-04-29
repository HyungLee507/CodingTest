import java.util.Scanner;
import java.util.*;
class Solution
{
    static boolean[][] visited ;
    static int[][] map;
    static int[] dir1 = {1, -1, 0, 0};
    static int[] dir2 = {0, 0, 1, -1};
    private static class Location{
        int x;
        int y;
        int depth;
        public Location(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
           	int size = sc.nextInt();
            map = new int[size][size];
            visited = new boolean[size][size];
            int total = 0;
            for(int i=0; i<size; i++){
                String str = sc.next();
                for(int j=0; j<size; j++){
                    map[i][j] = Character.getNumericValue(str.charAt(j));
                    total+= map[i][j];
                }
            }
            int minusValue = minimize(size);
			System.out.printf("#%d %d\n", test_case, total - minusValue);
		}
	}
    private static int minimize(int size){
        int maxDepth = size / 2;
        Queue<Location> queue = new LinkedList<>();
        if(maxDepth ==0){
            return 0;
        }
        queue.add(new Location(0, 0, 1));
        queue.add(new Location(0, size-1, 1));
        queue.add(new Location(size-1, 0, 1));
        queue.add(new Location(size -1, size-1, 1));
        visited[0][0] = true;
        visited[size-1][0] = true;
        visited[0][size-1] = true;
		visited[size-1][size-1] = true;
        int minusSum = map[0][0] + map[0][size-1] + map[size-1][0]  + map[size-1][size-1];
        while(!queue.isEmpty()){
            Location temp = queue.poll();
            int nowX = temp.x;
			int nowY = temp.y;
            for(int i = 0; i < 4; i++){
                int nextX = nowX + dir1[i];
                int nextY = nowY + dir2[i];
                int nowDepth = temp.depth;
               if(nextX >=0 && nextX < size && nextY >=0 && nextY < size && !visited[nextX][nextY] && nowDepth< maxDepth){
                   visited[nextX][nextY] = true;
                  	queue.add(new Location(nextX, nextY, nowDepth+1));
                   minusSum += map[nextX][nextY];
               }
            }
        }
            return minusSum;
    }
    
}