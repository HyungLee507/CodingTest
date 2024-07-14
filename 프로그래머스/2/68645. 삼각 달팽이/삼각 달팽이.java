import java.util.*;
class Solution {
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int value = 1;
        int x = -1, y = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) { // Downward
                    x++;
                } else if (i % 3 == 1) { // Rightward
                    y++;
                } else if (i % 3 == 2) { // Upward
                    x--;
                    y--;
                }
                triangle[x][y] = value++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result.add(triangle[i][j]);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}