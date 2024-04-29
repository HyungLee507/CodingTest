import java.util.Scanner;
import java.util.*;
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            List<Integer> numbers = new ArrayList<>();
            int numberCount = sc.nextInt();
            for(int i =0; i< numberCount; i++){
                numbers.add(sc.nextInt());
            }
            int orderCount = sc.nextInt();
            for (int i=0; i<orderCount; i++){
                String temp = sc.next();

                if(temp.equals("I")){
                    int start = sc.nextInt();
                    List<Integer> addList = new ArrayList<>();
                    int count = sc.nextInt();
                    for(int j =0; j<count;j++){
                        addList.add(sc.nextInt());
                    }
                    numbers.addAll(start,addList);
                }
                if(temp.equals("A")){
                    int count = sc.nextInt();
                    for(int j=0; j<count; j++){
                        numbers.add(sc.nextInt());
                    }
                }
                if(temp.equals("D")){
                    int index = sc.nextInt();
                    int count = sc.nextInt();
                    for(int j=0; j<count; j++){
                        numbers.remove(index);
                    }
                }
            }
            
            System.out.printf("#%d ",test_case);
            for(int i=0; i<10; i++){
				System.out.printf("%d ",numbers.get(i));
            }
            System.out.println();
		}
	}
}