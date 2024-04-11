import java.util.*;
class Solution {
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase();
        String step2 = step1.replaceAll("[^a-z0-9-_.]", "");
        String step3 = step2.replaceAll("\\.+", ".");
        StringBuilder sb = new StringBuilder(step3);
        if (sb.length() > 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.length() == 0) {
            sb.append('a');
        }
        if (sb.length() >= 16) {
            sb.delete(15, sb.length());
        }
        if (sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        char last = sb.charAt((sb.length() - 1));
        int iterationCount = 3 - sb.length();
        for (int i = 0; i < iterationCount; i++) {
            sb.append(last);
        }
        
        
        return sb.toString();
    }
}