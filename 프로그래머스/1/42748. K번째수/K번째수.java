import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int temp = 0;
        for (int[] command : commands) {
            int start = command[0] - 1;
            int end = command[1] - 1;
            List<Integer> aList = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                aList.add(array[i]);
            }
            Collections.sort(aList);
            answer[temp] = aList.get(command[2] - 1);
            temp++;
        }
        
        return answer;
    }
}