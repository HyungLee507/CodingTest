import java.util.*;
class Solution {
    List<Integer>[] adjust;
    int max = Integer.MIN_VALUE;
    int[] Info;
    public int solution(int[] info, int[][] edges) {
        adjust = new List[info.length];
        Info = Arrays.copyOf(info, info.length);
        for (int i = 0; i < info.length; i++) {
            adjust[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            adjust[start].add(end);
        }

        List<Integer> near = new ArrayList<>();
        near.add(0);
        dfs(0, 0, 0, near);
        return max;
    }
    private void dfs(int index, int sheep, int wolf, List<Integer> near) {
        if (Info[index] == 1) {
            wolf++;
        } else {
            sheep++;
        }
        if (wolf >= sheep) {
            return;
        } else {
            max = Integer.max(max, sheep);
        }
        List<Integer> nextStep = new ArrayList<>();
        nextStep.addAll(near);
        nextStep.remove(Integer.valueOf(index));
        for (int next : adjust[index]) {
            nextStep.add(next);
        }
        for (int i : nextStep) {
            dfs(i, sheep, wolf, nextStep);
        }
    }
}