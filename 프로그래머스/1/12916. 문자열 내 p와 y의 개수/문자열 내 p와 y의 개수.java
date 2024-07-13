class Solution {
    boolean solution(String s) {
        int pCount =0; 
        int yCount =0;
        for(int i=0; i<s.length();i++){
            char ch = Character.toLowerCase(s.charAt(i));
            if(ch == 'p'){
                pCount++;
            }else if(ch == 'y'){
                yCount++;
            }
        }
        if(pCount != yCount){
            return false;
        }
        return true;
    }
}