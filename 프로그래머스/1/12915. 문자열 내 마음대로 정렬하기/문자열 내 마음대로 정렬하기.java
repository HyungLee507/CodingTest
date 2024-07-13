import java.util.*;
class Solution {
    private static class Value implements Comparable<Value>{
        String str;
        int n;
        
        public Value(String str, int n){
            this.str = str;
            this.n = n;
        }
        @Override
        public int compareTo(Value o){
            if(this.str.charAt(n) == o.str.charAt(n)){
                return str.compareTo(o.str);
            }else{
               return Integer.compare(this.str.charAt(n), o.str.charAt(n)); 
            }
        }
        
        
    }
    public String[] solution(String[] strings, int n) {
        
        List<Value> values = new ArrayList<>();
        for(String string : strings){
            values.add(new Value(string, n));
        }
        Collections.sort(values);
        String[] answer = new String[strings.length];
        for(int i=0; i<answer.length; i++){
            answer[i] = values.get(i).str;
        }
        return answer;
    }
}