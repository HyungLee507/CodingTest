import java.util.*;
class Solution {
    public int[] solution(String s) {
        int idx = 1;
        Map<Integer, List<Integer>> numbers = new HashMap<>();
        List<Integer> temp = new ArrayList<>();
        while (idx < s.length() - 1) {
            if (s.charAt(idx) == '{') {
                temp.clear();
                idx++;
            } else if (s.charAt(idx) == '}') {
                numbers.put(temp.size(), new ArrayList<>(temp));
                idx++;
            } else {
                if (s.charAt(idx) == ',') {
                    idx++;
                    continue;
                }
                // index를 옮겨가며 값을 , 또는 '}' 이전까지의 숫자로만 된 문자열을 길이를 가져옴.
                int start = idx;
                int numberBound = idx;
                while (s.charAt(numberBound) != '}') {
                    if (s.charAt(numberBound) == ',') {
                        int num = Integer.parseInt(s.substring(start, numberBound));
                        temp.add(num);
                        start = numberBound + 1;
                    }
                    numberBound++;
                }
                int num = Integer.parseInt(s.substring(start, numberBound));
                temp.add(num);
                idx = numberBound;
            }
        }
        int[] answer = new int[numbers.size()];
        // 이제 튜플 집합 내에서 없는 놈들을 하나씩 answer에 순차적으로 넣음.
        List<Integer> compareList = new ArrayList<>();
        for(int i=0; i<numbers.size(); i++){
            List<Integer> getListBySize = numbers.get(i+1);
            // 중복되지 않는지 확인
            for(int j=0; j<compareList.size();j++){
                getListBySize.remove(Integer.valueOf(compareList.get(j)));
            }
            compareList.add(getListBySize.get(0));
            answer[i] = getListBySize.get(0);
        }
        
        
        
        
        return answer;
    }
}