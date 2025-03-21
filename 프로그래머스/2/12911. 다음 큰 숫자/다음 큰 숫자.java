class Solution {
    public int solution(int n) {
        int bits = Integer.bitCount(n);
        int answer = n+1;
        while(true){
            int bitCount = Integer.bitCount(answer);
            if(bitCount == bits){
                break;
            }
            answer++;
        }
        return answer;
    }
}