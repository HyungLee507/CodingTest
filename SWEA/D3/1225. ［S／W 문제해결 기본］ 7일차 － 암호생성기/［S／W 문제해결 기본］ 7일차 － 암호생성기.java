import java.util.Scanner;
import java.util.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int testNum = sc.nextInt();
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                queue.add(sc.nextInt());
            }
            int start = 1;
            while(true){
                if(start > 5){
                    start =1;
                }
               	int poll = queue.poll();
				if(poll - start >0){
                    queue.add(poll - start);
                    start++;
                }else{
                     queue.add(0);
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(" ");
           	for (int temp : queue){
               	sb.append(temp).append(" ");
            }
            System.out.println(sb);
		}
	}
}