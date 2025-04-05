class Solution {
    public int solution(String s) {    
        int idx=0;
        int cnt=0;
        while(true){
            char start = s.charAt(idx);
            int temp1 = 1;
            int temp2 = 0;
            int jump = 1;
            for(int i=idx+1; i<s.length(); i++) {
                char next = s.charAt(i);
                if(next == start){
                    temp1++;
                }else{
                    temp2++;
                }
                jump++;
                if(temp1==temp2){   
                    break;
                }
            }
            idx += jump;
            System.out.println(idx);
            cnt++;
            if(idx == s.length()){
                break;
            }
            if(idx == s.length()-1){
                cnt++;
                break;
            }
        }
        return cnt;
    }
}