import java.util.*;
class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder changeSb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                sb.append(s.charAt(i));
                continue;
            }
            changeSb.append(s.charAt(i));
            if(matchDigit(changeSb.toString())){
                int number = changeToNumber(changeSb.toString());
                sb.append(number);
                changeSb = new StringBuilder();
            }
        }
        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
    private boolean matchDigit(String str){
        if(str.equals("zero") ||str.equals("one")||str.equals("two")||str.equals("three")||str.equals("four")||str.equals("five")||str.equals("six")||str.equals("seven")|| str.equals("eight")||str.equals("nine")){
            return true;
        }
        return false;
    }
    private int changeToNumber(String str){
        if(str.equals("zero")){
            return 0;
        }
        if(str.equals("one")){
            return 1;
        }
        if(str.equals("two")){
            return 2;
        }
        if(str.equals("three")){
            return 3;
        }
        if(str.equals("four")){
            return 4;
        }
        if(str.equals("five")){
            return 5;
        }
        if(str.equals("six")){
            return 6;
        }
        if(str.equals("seven")){
            return 7;
        }
        if(str.equals("eight")){
            return 8;
        }
        if(str.equals("nine")){
            return 9;
        }
        return -1;
        
    }
    
}