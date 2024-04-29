import java.util.Scanner;
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int testCount = sc.nextInt();
            String searchStr = sc.next();
            String str = sc.next();
            int index = 0;
            int count = 0;
            while(true){
                if(str.indexOf(searchStr, index)!=-1){
                    index = str.indexOf(searchStr, index) + searchStr.length();
                    count++;
                }else{
                	break;
                }
            }
            System.out.printf("#%d %d\n", test_case, count);
		}
	}
}