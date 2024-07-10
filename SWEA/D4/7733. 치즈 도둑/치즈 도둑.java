import java.util.Scanner;
import java.io.*;
import java.util.*;

class Solution
{
    static int n;
    static int[][] map;
    static boolean[][] eaten;
    static StringTokenizer st;
    static BufferedReader bf;
    private static final int[] dirX = {0,0,1,-1};
    private static final int[] dirY = {1,-1,0,0};
	public static void main(String args[]) throws Exception
	{
		bf = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(bf.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            n = Integer.parseInt(bf.readLine());
            init();
            int max =1;
           	for(int i= 1; i<100; i++){
                int totalCount = search(i);
                max = Integer.max(max, totalCount);
            }
            System.out.printf("#%d %d\n", test_case, max);
		}
	}
    private static void init() throws IOException{
        map = new int[n][n];
        eaten = new boolean[n][n];
        for(int i=0; i<n;i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++){
        		map[i][j] = Integer.parseInt(st.nextToken());       
            }
        }
    }
    private static int search(int day){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == day){
                    eaten[i][j] = true;
                }
            }
        }
        int count =0; 
       	boolean[][] temp = new boolean[n][n];
        for(int i=0; i<n; i++){
            temp[i] = Arrays.copyOf(eaten[i],eaten[i].length);
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!temp[i][j] && map[i][j] >day){
                    temp[i][j] = true;
                    temp = bfs(i,j, day,temp);
                    //eaten[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean[][] bfs(int x, int y, int day, boolean[][] temp){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i=0; i<4; i++){
                int nextX = now[0] + dirX[i];
                int nextY = now[1] + dirY[i];
                if(nextX >=0 && nextY>=0 && nextX <n && nextY < n &&  !temp[nextX][nextY] && map[nextX][nextY] > day){
                    queue.add(new int[]{nextX, nextY});
                    temp[nextX][nextY] = true;
                    //eaten[nextX][nextY] = true;
                }
            }
        }
        return temp;
    }
}