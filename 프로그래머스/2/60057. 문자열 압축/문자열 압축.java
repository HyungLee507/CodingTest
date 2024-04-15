import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        for (int i=1; i<= s.length() /2 ; i++){
            answer = Integer.min(answer , cutBylen(s,i));
        }
        return answer;
    }
    private int cutBylen(String s, int len) {
        StringBuilder sb = new StringBuilder();
        int continueCount = 1;
        String subStr = s.substring(0, len);
        for (int i = 1; i <= s.length() / len; i++) {
            String temp = s.substring(len * i, Math.min(len * (i + 1), s.length()));
            if (subStr.equals(temp)) {
                continueCount++;
            } else {
                if (continueCount == 1) {
                    sb.append(subStr);
                } else {
                    sb.append(continueCount).append(subStr);
                }
                continueCount = 1;
                subStr = temp;
            }
        }
        if (continueCount == 1) {
            sb.append(subStr);
        } else {
            sb.append(continueCount).append(subStr);
        }
        return sb.length();

    }
}