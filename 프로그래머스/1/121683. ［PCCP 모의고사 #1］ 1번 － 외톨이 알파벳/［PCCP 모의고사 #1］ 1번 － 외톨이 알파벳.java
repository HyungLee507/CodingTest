import java.util.*;
class Solution {
    public String solution(String input_string) {
        List<Character> exist = new ArrayList<>();
        List<Character> notAlone = new ArrayList<>();
        int idx = 0;
        while (idx < input_string.length()) {
            char ch = input_string.charAt(idx);
            if (!exist.contains(ch)) {
                exist.add(ch);
                while (true) {
                    if (idx + 1 < input_string.length() && input_string.charAt(idx + 1) == ch) {
                        idx++;
                    } else {
                        break;
                    }
                }
            } else {
                if (!notAlone.contains(ch)) {
                    notAlone.add(ch);
                }
            }
            idx++;
        }
        if (notAlone.size() == 0) {
            return "N";
        } else {
            StringBuilder sb = new StringBuilder();
            Collections.sort(notAlone);
            for (Character c : notAlone) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
}