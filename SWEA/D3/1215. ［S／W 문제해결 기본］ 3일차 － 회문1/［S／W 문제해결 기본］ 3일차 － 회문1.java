import java.util.*;
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		char[][] map ;
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int size = sc.nextInt();
            int result = 0;
           	map = new char[8][8];
           	for(int i=0; i<8; i++){
                String str = sc.next();
                for(int j=0; j<8; j++){
                    map[i][j] = str.charAt(j);
                }
            }
            for(int i=0;  i<8;  i++){
                for(int j=0; j<8; j++){
                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    for(int k=0; k<size;k++){
                       if (i <= 8 - size) {
                            sb1.append(map[i + k][j]);
                        }
                        if (j <= 8 - size) {
                            sb2.append(map[i][j + k]);
                        }
                    }
                    if (check(sb1.toString()) && i <= 8 - size) {
                        result++;
                    }
                    if (check(sb2.toString()) && j <= 8 - size) {
                        result++;
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, result);
		}
	}
    private static boolean check(String str){
        for(int i=0; i<str.length()/2; i++){
            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }
}