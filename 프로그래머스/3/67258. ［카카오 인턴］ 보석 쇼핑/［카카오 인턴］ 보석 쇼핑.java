import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        List<String> list = Arrays.asList(gems);
        Set<String> gemSet = new HashSet<>(list);
        int markSize = gemSet.size();
        int[] answer = new int[]{1, gems.length};
        int start = 0;
        int end = 0;
        Map<String, Integer> markMap = new HashMap();
        while (start <= end && end < gems.length) {
            int size = markMap.size();
            if (size < markSize) {
                markMap.put(gems[end], markMap.getOrDefault(gems[end], 0) + 1);
                if (markMap.size() != markSize) {
                    end++;
                }
            } else {
                removeData(markMap, gems[start]);
                answer = updateIndex(start, end, answer);
                if (markMap.size()!= markSize) {
                    end++;
                }
                start++;
            }
        }
        return answer;
    }
    private int[] updateIndex(int start, int end, int[] answer) {
        if ((end - start) >= answer[1] - answer[0]) {
            return answer;
        } else {
            return new int[]{start + 1, end + 1};
        }
    }
    private void removeData(Map<String, Integer> markMap, String gem) {
        int value = markMap.get(gem);
        if(value ==1){
            markMap.remove(gem);
            return;
        }
        markMap.put(gem, value - 1);
    }

    private int check(Map<String, Integer> markMap) {
        int gemCount = 0;
        for (int value : markMap.values()) {
            if (value > 0) {
                gemCount++;
            }
        }
        return gemCount;
    }
}