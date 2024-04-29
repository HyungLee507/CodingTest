import java.util.Arrays;
import java.util.Scanner;

class Solution {
    static int[] snacks;
    static int totalMaxWeight;
    static int boughtWeight;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int snackCount = sc.nextInt();
            snacks = new int[snackCount];
            totalMaxWeight = sc.nextInt();
            boughtWeight = Integer.MIN_VALUE;
            for (int i = 0; i < snackCount; i++) {
                snacks[i] = sc.nextInt();
            }
            Arrays.sort(snacks);
            int left = 0;
            int right = snackCount - 1;

            while (left < right) {
                int price1 = snacks[left];
                int price2 = snacks[right];
                if (price1 + price2 > boughtWeight) {
                    if (price1 + price2 <= totalMaxWeight) {
                        boughtWeight = Math.max(boughtWeight, price1 + price2);
                        left++;
                    } else {
                        right--;
                    }
                } else {
                    left++;
                }
            }
            if (boughtWeight == Integer.MIN_VALUE) {
                boughtWeight = -1;
            }
            System.out.printf("#%d %d\n", test_case, boughtWeight);
        }
    }

}