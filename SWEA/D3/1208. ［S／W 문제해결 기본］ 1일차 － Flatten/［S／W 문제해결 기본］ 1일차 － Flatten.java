import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++){
            int dumpCount = sc.nextInt();
            int[] arr = new int[101];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i=0; i<100; i++){
                int temp = sc.nextInt();
				arr[temp]++;             
                if(temp < min){
                    min = temp;
                }
                if(temp > max){
                    max = temp;
                }
            }
           for (int i = 0; i < dumpCount; i++) {
                arr[max]--;
                arr[max - 1]++;
                arr[min]--;
                arr[min + 1]++;
                if (arr[max] == 0) {
                    max = getMaxIndex(arr);
                }
                if (arr[min] == 0) {
                    min = getMinIndex(arr);
                }
            }
			System.out.printf("#%d %d\n", test_case, max-min);
		}
	}
    private static int getMaxIndex(int[] arr) {
        int start = arr.length - 1;
        while (arr[start] == 0) {
            start--;
        }
        return start;
    }

    private static int getMinIndex(int[] arr) {
        int start = 0;
        while (arr[start] == 0) {
            start++;
        }
        return start;
    }
}