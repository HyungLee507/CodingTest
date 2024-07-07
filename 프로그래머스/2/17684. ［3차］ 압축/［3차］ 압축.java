import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            dict.put(String.valueOf((char) ('A' + (i - 1))), i);
        }
        List<Integer> answer = new ArrayList<>();
        int now = 0;
        int index = 27;
        while (now < msg.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(now));
            int start = now;
            while (dict.containsKey(sb.toString()) && start < msg.length() - 1) {
                start++;
                sb.append(msg.charAt(start));
            }
            if (!dict.containsKey(sb.toString())) {
                dict.put(sb.toString(), index);
                index++;
            } else {
                if (start == msg.length() - 1) {
                    now += sb.length();
                    answer.add(dict.get(sb.toString()));
                    continue;
                }
            }
            if (sb.length() > 1) {
                int removeIndex = sb.length() - 1;
                sb.deleteCharAt(removeIndex);
            }
            now += sb.length();
            answer.add(dict.get(sb.toString()));
        }
        int[] result = new int[answer.size()];
        for(int i=0; i<result.length; i++){
            result[i] = answer.get(i);
        }
        return result;
    }
}