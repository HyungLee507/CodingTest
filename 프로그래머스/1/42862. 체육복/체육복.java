import java.util.*;
import java.util.stream.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        Set<Integer> collect1 = Arrays.stream(lost).boxed().collect(Collectors.toSet());
        Set<Integer> collect2 = Arrays.stream(reserve).boxed().collect(Collectors.toSet());
        List<Integer> duplicate = new ArrayList<>();
        for(int i : collect1){
            if(collect2.contains(i)){
                duplicate.add(i);
            }
        }
        int answer = n - lost.length + duplicate.size();
        int lostIndex = 0;
        int reserveIndex = 0;
        
        while(lostIndex <lost.length && reserveIndex < reserve.length){
            int reserveSize = reserve[reserveIndex];
            int needSize = lost[lostIndex];
            if (duplicate.contains(reserveSize) || duplicate.contains(needSize)) {
                if (duplicate.contains(reserveSize)) {
                    reserveIndex++;
                }
                if (duplicate.contains(needSize)) {
                    lostIndex++;
                }
                continue;
            }
            if(needSize == reserveSize + 1 || needSize == reserveSize - 1){
                answer++;
                reserveIndex++;
                lostIndex++;
            }else{
                if(needSize > reserveSize + 1 ){
                    reserveIndex++;
                }else if(needSize < reserveSize -1 ){
                    lostIndex++;
                }
            }
        }
        return answer;
    }
}