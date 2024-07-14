import java.util.*;
class Solution {
    int index = 1;
    Map<String, Integer> dict = new HashMap<>();
    public static final String[] ALPHABET ={"A", "E", "I", "O", "U"};
    public int solution(String word) {
        setDict("");
        int answer = dict.get(word);       
        return answer;
    }
    private void setDict(String str){
        if (str.length() == 5) {
            return;
        }
        for(int i=0; i<5; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(str).append(ALPHABET[i]);
            dict.put(sb.toString(), index++);
            setDict(sb.toString());
        }
    }
}