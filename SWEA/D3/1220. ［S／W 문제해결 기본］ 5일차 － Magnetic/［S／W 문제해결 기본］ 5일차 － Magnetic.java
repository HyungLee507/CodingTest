import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int deadLock = 0;
            int size = sc.nextInt();
            int[][] board = new int[100][100];
            for(int i=0; i<100; i++){
                for(int j=0; j<100; j++){
                    board[i][j] = sc.nextInt();
                }
            }
            for(int i=0; i<100; i++){
                boolean passing = false;
                for(int j=0; j<100; j++){
					if(board[j][i] == 1 && !passing){
                        passing = true;
                    }
                    if(board[j][i] == 2 && passing){
                        passing = false;
                        deadLock++;
                    }
                }
                
            }
			System.out.printf("#%d %d \n",test_case, deadLock);
		}
	}
}