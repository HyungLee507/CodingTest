import java.util.*;
class Solution {
    public String solution(String p) {
        return divideString(p);
    }
    
    private String divideString(String str){
        String[] strs = getStrings(str);
        String u = strs[0];
        String v = strs[1];
        StringBuilder sb = new StringBuilder();
        String next;
        if(v.length()!=0){
            next = divideString(v);
        }else{
            next = "";
        }
        if(isCorrectBraket(u)){
            sb.append(u);
            sb.append(next);
        }else{
            sb.append("(").append(next).append(")");
            for(int i=1; i<u.length()-1;i++){
                if(u.charAt(i) == '('){
                    sb.append(')');
                }else {
                    sb.append('(');
                }
            }
        }    
        return sb.toString();
    }
    
    private String[] getStrings(String str){
        String[] strs = new String[2];
        StringBuilder sb = new StringBuilder();
        int open = 0;
        int close = 0;
        int idx = 0;
        while(idx < str.length()){
            if(str.charAt(idx) == '('){
                open++;
                sb.append('(');
                idx++;
            }else if(str.charAt(idx) == ')'){
                close++;
                sb.append(')');
                idx++;
            }
            if(open !=0 && open == close){
                break;
            }
        }
        if(idx == str.length()){
            strs[0] = str;
            strs[1] = "";
        }else{
            strs[0] = str.substring(0, idx);
            strs[1] = str.substring(idx, str.length());
        }
        return strs;        
    }
    
    
    
    
    private boolean isCorrectBraket(String str){
        int open = 0;
        int close = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open < close) {
                return false;
            }
        }
        return true;
    }
}