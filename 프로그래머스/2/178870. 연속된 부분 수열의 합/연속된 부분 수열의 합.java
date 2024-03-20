import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int maxSum = 0;
        int[] answer = {0,sequence.length};
        int maxIndex = sequence.length;
        int start = 0;
        int end = 0;
        while(start < maxIndex && end < maxIndex){
             if (maxSum + sequence[end] < k) {
                maxSum += sequence[end];
                end++;
            } else if (maxSum + sequence[end] > k) {
                maxSum -= sequence[start];
                start++;
            } else if (maxSum + sequence[end] == k) {
                answer = changeValue(answer, start, end);
                maxSum -= sequence[start];
                start++;
            }
            
            
        }
        
        return answer;
    }
    
    
    public int[] changeValue(int[] answer, int first, int last){
        int diff1 = answer[1] - answer[0];
        int diff2 = last - first;
        
        if (diff1 > diff2){
            answer[0] = first;
            answer[1] = last;
        }
        return answer;
        
    }
}