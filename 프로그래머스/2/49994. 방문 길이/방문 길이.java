import java.util.*;
class Solution {
    
    private static class Location{
        int x; 
        int y;
        List<Edge> edges;
        
        public Location(int x, int y){
            this.x = x;
            this.y = y;
            edges = new ArrayList<>();
        }
        private void move(char instruction){
            int[] now = new int[]{x,y};
            if(instruction == 'U'){
                if(x>=5){
                    return;
                }else{
                    this.x = this.x+1;    
                }
            }
            if(instruction == 'D'){
                if(x<=-5){
                    return;
                }else{
                    this.x = this.x-1;    
                }
            }
            if(instruction == 'R'){
                if(y>=5){
                    return;
                }else{
                    this.y = this.y+1;    
                }
            }
            if(instruction == 'L'){
                if(y<=-5){
                    return;
                }else{
                    this.y = this.y-1;    
                }
            }
            int[] next = new int[]{x,y};
            Edge edge = new Edge(now, next);
            if(!edges.contains(edge)){
                edges.add(edge);
            }
        }
    }
    private static class Edge{
        int[] start;
        int[] end;
        
        public Edge(int[] start, int[] end){
            this.start = start;
            this.end = end;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return Objects.deepEquals(start, edge.start) && Objects.deepEquals(end, edge.end)
                    || Objects.deepEquals(start, edge.end) && Objects.deepEquals(end, edge.start);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(start), Arrays.hashCode(end));
        }
        
        
    }
    
    
    
    public int solution(String dirs) {
        Location location = new Location(0,0);
        for(int i=0; i<dirs.length();i++){
            location.move(dirs.charAt(i));
        }
        return location.edges.size();
    }
}