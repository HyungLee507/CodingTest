import java.util.*;
class Solution {
    
    private static class Section{
        int start;
        int end;
        
        public Section(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public int solution(int[][] routes) {
        Stack<Section> sections = new Stack<>();
        for(int[] route: routes){
            sections.add(new Section(route[0], route[1]));
        }
        sections.sort((s1, s2) -> s2.start - s1.start);
        return getNeedCamera(sections);
        
    }
    private int getNeedCamera(Stack<Section> sections){
        int count = 1;
        Section temp = sections.pop();
        while(!sections.isEmpty()){
            Section next = sections.pop();
            if(next.start <= temp.end){
                temp.start = next.start; 
                if(next.end <= temp.end){
                    temp.end = next.end;
                }
            }else{
                count++;
                temp = next;
            }
            
        }
        return count;
    }
}