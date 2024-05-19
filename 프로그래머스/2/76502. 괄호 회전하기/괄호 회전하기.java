import java.util.*;
class Solution {
    public int solution(String s) {
        int answer =0;
        int n = s.length();
        for(int i=0; i<n; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=i; j<n; j++){
                sb.append(s.charAt(j));
            }
            for(int j=0; j<i;j++){
                sb.append(s.charAt(j));
            }
            if(isCorrect(sb.toString())){
                answer++;
            }
        }
        return answer;
    }
    private boolean isCorrect(String str){
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=0; i<str.length();i++){
            if(str.charAt(i)=='('||str.charAt(i)=='['||str.charAt(i)=='{'){
                stack.push(str.charAt(i));
            }else{
                if (stack.isEmpty()) {
                    return false;
                }
                char temp = stack.pop();
                if(temp=='(' && str.charAt(i) !=')'){
                    return false;
                }
                if(temp=='{' && str.charAt(i) !='}'){
                    return false;
                }
                if(temp=='[' && str.charAt(i) !=']'){
                    return false;
                }
            }
        }
        if(!stack.isEmpty()){
            return false;
        }else{
            return true;    
        }
    }
}