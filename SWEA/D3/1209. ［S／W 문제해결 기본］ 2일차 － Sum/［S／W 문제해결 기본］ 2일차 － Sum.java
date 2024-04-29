import java.util.Scanner;
import java.io.FileInputStream;
class Solution
{
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);
		for(int test_case = 1; test_case <= 10; test_case++){
            int testIndex = sc.nextInt();
            int maxSum = Integer.MIN_VALUE;
			int[][] arr = new int[101][101];
            for(int i=1;i<=100; i++){
                for(int j=1; j<=100; j++){
                    arr[i][j] = arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1] + sc.nextInt();
                }
                maxSum = Math.max(maxSum, arr[i][100] - arr[i - 1][100]);
            }
            int leftSum =0;
            int rightSum =0;
            for(int i=1; i<101;i++){
                maxSum = Math.max(maxSum, arr[100][i] - arr[100][i-1]);
                leftSum = leftSum + arr[i][i] + arr[i-1][i-1] - arr[i-1][i] - arr[i][i-1];
                rightSum = rightSum + arr[i][101- i] + arr[i-1][100-i] - arr[i-1][101-i] - arr[i][100-i];
            }
            maxSum = Math.max(maxSum, leftSum);
            maxSum = Math.max(maxSum, rightSum);
            
		System.out.printf("#%d %d\n", test_case, maxSum);
		}
	}
}