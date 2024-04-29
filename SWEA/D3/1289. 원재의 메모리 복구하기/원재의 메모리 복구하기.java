import java.util.Scanner;
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String memory = sc.next();
            char temp = '0';
            int count = 0;
            for(int i=0; i<memory.length();i++){
                if(memory.charAt(i)!=temp){
                    count++;
                    temp = memory.charAt(i);
                }
            }
            System.out.printf("#%d %d\n", test_case, count);
		}
	}
}