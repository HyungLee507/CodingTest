import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split("");
        int idx = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(" ")) {
                idx = 1;
                sb.append(strs[i]);
                idx++;
                continue;
            }            
            if(idx % 2 ==0 ){
                sb.append(strs[i].toUpperCase());
            }else{
                sb.append(strs[i].toLowerCase());
            }
            idx++;
        }

        return sb.toString();
    }
}