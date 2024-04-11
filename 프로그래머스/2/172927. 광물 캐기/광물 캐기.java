import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        List<int[]> resoures = new ArrayList<>();
        int[] subSum = new int[3];
        for (int i=0; i< minerals.length; i++){
            int index = i;
            if(i != 0 && i % 5 == 0){
                resoures.add(subSum);
                subSum = new int[3];
            }
            if(minerals[index].equals("diamond")){
                subSum[0] +=1;
            }
            if(minerals[index].equals("iron")){
                subSum[1] +=1;
            }
            if(minerals[index].equals("stone")){
                subSum[2] +=1;
            }
        }
        resoures.add(subSum);
        if ((picks[0] + picks[1] + picks[2]) < resoures.size()) {
            resoures = new ArrayList<>(resoures.subList(0, picks[0] + picks[1] + picks[2]));
        }
        resoures.sort(Comparator.comparing((int[] arr) -> arr[0]).reversed()
                .thenComparing(arr -> arr[1], Comparator.reverseOrder()));
    
        for (int[] values : resoures){
            if(picks[0] > 0){
                answer = answer + values[0] + values[1] + values[2];
                picks[0]--;
                continue;
            }
            if(picks[1] > 0){
                answer = answer + values[0] * 5 + values[1] + values[2];
                picks[1]--;
                continue;
            }
            if(picks[2] > 0){
                answer = answer + values[0] * 25 + values[1] * 5 + values[2];
                picks[2]--;
                continue;
            }
            
        } 
        return answer;
    }
}