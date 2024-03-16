import java.util.Set;
import java.util.HashSet;
import java.util.List;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
            
        for(int i = 0; i < photo.length;  i++){
            String[] peoples = photo[i];
            Set<String> nameSet = new HashSet<>(List.of(peoples));
            for (int j = 0; j < name.length; j++){
                if(nameSet.contains(name[j])){
                    answer[i] += yearning[j];
                }
            }
        }
        return answer;
    }
}