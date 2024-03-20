import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        List<int[]> counterAttackRanges = new ArrayList<>();
        Arrays.sort(targets, (t1, t2) -> {
            return t1[1] - t2[1];
        });
        for (int[] target : targets){
            if(counterAttackRanges.isEmpty()){
                counterAttackRanges.add(target);
                continue;
            }
            boolean isAddedRange = true;
            for (int[] range : counterAttackRanges) {
                if (target[1] <= range[0] || target[0] >= range[1]) {
                    continue;
                }
                if (target[0] >= range[0]) {
                    range[0] = target[0];
                }
                if (target[1] <= range[1]) {
                    range[1] = target[1];
                }
                isAddedRange = false;
                break;
            }
            if (isAddedRange) {
                counterAttackRanges.add(target);
            }
        }
        int answer = counterAttackRanges.size();
        return answer;
    }
}