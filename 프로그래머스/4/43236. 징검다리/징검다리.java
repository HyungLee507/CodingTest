import java.util.*;
class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int start = 0; 
        int end = distance;
        int min=0;
        while(start <= end){
            int mid = ( start + end ) /2;
            int count = getBrokenRocks(rocks,mid, distance);
            if(count <=n){
                min = mid;
                start = mid+1;
            }else{
                end = mid -1;
            }
        }
        return min;
        
    }
    
    public int getBrokenRocks(int[] rocks, int mid, int distance){
        int before = 0; 
        int end = distance;
        
        int removeCnt = 0;
        for(int i = 0; i < rocks.length; i++){
             if(rocks[i] - before < mid) {
                removeCnt++;
                 continue;
            }
            before = rocks[i];
        }
        if(end - before < mid) removeCnt++;

        return removeCnt;
    }
}