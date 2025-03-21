class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;
        
        for (int y = 3; y <= Math.sqrt(total); y++) {
            if (total % y != 0) continue;
            int x = total / y;
            
            if ((x - 2) * (y - 2) == yellow) {
                answer[0]=x;
                answer[1]=y;
            }
        }
        return answer;
    }
}