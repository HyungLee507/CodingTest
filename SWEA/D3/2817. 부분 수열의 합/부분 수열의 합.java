import java.util.Scanner;

 class Solution {
    // boolean[] visited;
    static int[] arr;
    static int count;
    static int k;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int numberCount = sc.nextInt();
            k = sc.nextInt();
            arr = new int[numberCount];
            //visited = new boolean[numberCount];
            count = 0;
            for (int i = 0; i < numberCount; i++) {
                arr[i] = sc.nextInt();
            }
            dfs(0, 0);
            System.out.printf("#%d %d\n", test_case, count);

        }
    }

    private static void dfs(int index, int sum) {
        if (index == arr.length || sum > k) {
            return;
        }
        if (sum + arr[index] == k) {
            count++;
        }
        dfs(index + 1, sum + arr[index]);
        dfs(index + 1, sum);

    }
}