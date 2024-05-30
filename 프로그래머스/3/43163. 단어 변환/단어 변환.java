import java.util.*;
class Solution {
    List<String> wordList = new ArrayList<>();
    int answer = Integer.MAX_VALUE;
    String targetStr;
    boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        targetStr = target;
        for(String word : words){
            wordList.add(word);
        }
        if(!wordList.contains(target)){
            return 0;
        }
        visited = new boolean[words.length];
        dfs(begin,0);
        if(answer == Integer.MAX_VALUE){
            return 0;
        }else{
            return answer;
        }
    }
    private void dfs(String str ,int depth){
        if(str.equals(targetStr)){
            answer = Math.min(depth,answer);
            return;
        }
        for(int i=0; i<wordList.size(); i++){
            if(!visited[i] && isDiffOneLetter(str,wordList.get(i))){
                visited[i] = true;
                dfs(wordList.get(i), depth+1);
                visited[i] = false;
            }
        }
    }
    private boolean isDiffOneLetter(String now, String next){
        int count =0;
        for(int i=0; i<now.length(); i++){
            if(now.charAt(i) != next.charAt(i)){
                count++;
            }
        }
        if(count == 1){
            return true;
        }else{
            return false;
        }
    }
}