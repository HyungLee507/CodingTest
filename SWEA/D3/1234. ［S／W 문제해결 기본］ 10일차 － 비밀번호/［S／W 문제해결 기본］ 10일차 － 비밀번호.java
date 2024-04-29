import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int length = sc.nextInt();
            String str = sc.next();
            while (hasSubStr(str) != -1) {
                str = extracted(str, hasSubStr(str));
            }
            System.out.printf("#%d %s\n", test_case, str);
        }

    }

    private static int hasSubStr(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                return i;
            }
        }
        return -1;
    }

    private static String extracted(String str, int i) {
        int[] index = getSubIndex(str, i, i + 1);
        return str.replaceFirst(str.substring(index[0], index[1] + 1), "");
    }

    private static int[] getSubIndex(String str, int left, int right) {
        while (left - 1 >= 0 && right + 1 < str.length()) {
            if (str.charAt(left - 1) == str.charAt(right + 1)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left, right};
    }


}