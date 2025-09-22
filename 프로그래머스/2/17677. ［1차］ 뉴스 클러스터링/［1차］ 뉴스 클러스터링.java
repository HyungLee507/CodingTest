import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> sub1 = toBigrams(str1);
        Map<String, Integer> sub2 = toBigrams(str2);

        Map<String, Integer> total = new HashMap<>();       
        Map<String, Integer> inter = new HashMap<>();       
        
        for (String k : sub1.keySet()) {
            int c1 = sub1.get(k);
            total.put(k, c1); 
            if (sub2.containsKey(k)) {
                int c2 = sub2.get(k);
                inter.put(k, Math.min(c1, c2)); 
            }
        }

        for (String k : sub2.keySet()) {
            int c2 = sub2.get(k);
            int cur = total.getOrDefault(k, 0);
            if (c2 > cur) total.put(k, c2);
        }

        int unionCnt = 0;
        for (int v : total.values()) unionCnt += v;

        int interCnt = 0;
        for (int v : inter.values()) interCnt += v;

        if (unionCnt == 0) return 65536;

        return (int) Math.floor((double) interCnt / unionCnt * 65536);
    }

    private Map<String, Integer> toBigrams(String s) {
        Map<String, Integer> map = new HashMap<>();
        s = s.toLowerCase();
        for (int i = 0; i < s.length() - 1; i++) {
            String bi = s.substring(i, i + 2);
            if (isAlphaPair(bi)) {
                map.put(bi, map.getOrDefault(bi, 0) + 1);
            }
        }
        return map;
    }

    private boolean isAlphaPair(String bi) {
        if (bi.length() != 2) return false;
        char a = bi.charAt(0), b = bi.charAt(1);
        return isAlpha(a) && isAlpha(b);
    }

    private boolean isAlpha(char c) {
        return c >= 'a' && c <= 'z';
    }
}