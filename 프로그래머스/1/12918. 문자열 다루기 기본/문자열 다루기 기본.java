class Solution {
    public boolean solution(String s) {
        if(s.length() != 4 && s.length() != 6){
            return false;
        }
        boolean answer = true;
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!Character.isDigit(ch)){
                answer = false;
                break;
            }
        }
        return answer;
    }
}