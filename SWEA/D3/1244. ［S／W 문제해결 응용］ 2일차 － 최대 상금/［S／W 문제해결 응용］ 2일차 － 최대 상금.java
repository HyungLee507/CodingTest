import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int exchangeCount ;
    static int result ;
    static boolean[][] visited;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            String number = sc.next();
            exchangeCount = sc.nextInt();
            result = Integer.MIN_VALUE;
            visited = new boolean[exchangeCount][(int) Math.pow(10, number.length())];
            getMaxNumber(number, 0);
            System.out.printf("#%d %d\n",test_case, result);
		}
	}
    private static void getMaxNumber(String number, int count) {
        if (count == exchangeCount) {
            result = Math.max(Integer.parseInt(number), result);
            return;
        }
        for (int i = 0; i < number.length(); i++) {
            char temp1 = number.charAt(i);
            for (int j = i + 1; j < number.length(); j++) {
                char temp2 = number.charAt(j);
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < number.length(); k++) {
                    if (k == i) {
                        sb.append(temp2);
                    } else if (k == j) {
                        sb.append(temp1);
                    } else {
                        sb.append(number.charAt(k));
                    }
                }
                if (!visited[count][Integer.parseInt(sb.toString())]) {
                    visited[count][Integer.parseInt(sb.toString())] = true;
                    getMaxNumber(sb.toString(), count + 1);
                }
//                getMaxNumber(sb.toString(), count + 1);
            }
        }
    }
}