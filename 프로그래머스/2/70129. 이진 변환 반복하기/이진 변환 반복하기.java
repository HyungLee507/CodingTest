class Solution {
    public int[] solution(String s) {
        int iterCount = 0;
        int removeCount = 0;
        
        while(!s.equals("1")){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) =='0'){
                    removeCount++;
                }
                if(s.charAt(i) =='1'){
                    sb.append(1);
                }
            }
            s = Integer.toString(sb.length(),2);
            iterCount++;
        }
        int[] answer = new int[2];
        answer[0] = iterCount; answer[1] = removeCount;
        return answer;
    }
}