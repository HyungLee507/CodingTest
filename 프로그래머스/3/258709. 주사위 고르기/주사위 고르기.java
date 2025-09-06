import java.util.*;

class Solution {
    int iterCnt;
    double winRateMax;
    int[] ans;
    int[][] dices;

    public int[] solution(int[][] dice) {
        dices = dice;
        iterCnt = dice.length;
        ans = new int[iterCnt / 2];
        winRateMax = -1.0;
        dfs(0,new ArrayList<>());
        Arrays.sort(ans);
        for (int i = 0; i < ans.length; i++) ans[i] += 1; 
        return ans;
    }

    public void dfs(int depth, List<Integer> comb){
        if (comb.size() == iterCnt / 2) {
            getResult(comb);
            return;
        }
        if (depth == iterCnt) return;

        comb.add(depth);
        dfs(depth+1, comb);
        comb.remove(comb.size()-1);
        dfs(depth+1, comb);
    }
    
    public void getResult(List<Integer> comb){
        List<Integer> other = new ArrayList<>();
        for (int i = 0; i < iterCnt; i++) {
            if (!comb.contains(i)) other.add(i);
        }

        List<Integer> sumA = getAllSums(comb);
        List<Integer> sumB = getAllSums(other);

        Collections.sort(sumB);

        int win = 0, draw = 0, lose = 0;
        for (int a : sumA) {
            int less = lowerBound(sumB, a);                   
            int equal = upperBound(sumB, a) - less;           
            win += less;
            draw += equal;
            lose += sumB.size() - less - equal;               
        }

        double winRate = (double) win / (sumA.size() * sumB.size());

        if (winRate > winRateMax) {
            winRateMax = winRate;
            ans = comb.stream().mapToInt(i -> i).toArray();
        }
    }
    
    private List<Integer> getAllSums(List<Integer> indices) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int idx : indices) {
            List<Integer> next = new ArrayList<>();
            for (int base : result) {
                for (int face : dices[idx]) {
                    next.add(base + face);
                }
            }
            result = next;
        }
        return result;
    }

    private int lowerBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) < target) {
                l = mid + 1;  
            } 
            else r = mid;
        }
        return l;
    }

    private int upperBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) <= target) {
                l = mid + 1;   
            }
            else r = mid;
        }
        return l;
    }
}