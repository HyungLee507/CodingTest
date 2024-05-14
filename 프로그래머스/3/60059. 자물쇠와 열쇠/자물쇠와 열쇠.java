import java.util.*;
class Solution {
    
   public boolean solution(int[][] key, int[][] lock) {
        for(int i=0;i<4;i++){
            if(i>0) key = rotate(key);
            for(int x=-(lock.length-1);x<(lock.length-1); x++){
                for(int y=-(lock.length-1);y<(lock.length-1); y++){
                    if(isCorrect(key,lock,x,y)) return true;
                }
            }
        }
        return false;
    }
    private int[][] rotate(int[][] key){
        int[][] result = new int[key.length][key.length];
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                result[i][j] = key[key.length-1-j][i];
            }
        }
        return result;
    }
    private boolean isCorrect(int[][] key, int[][] lock, int x, int y){
        for(int i=0;i<lock.length;i++){
            for(int j=0;j<lock.length;j++){
                if(i+x<0 || i+x>=key.length || j+y<0 || j+y>=key.length){
                    if(lock[i][j]==0) return false;
                }else{
                    if(lock[i][j] == key[i+x][j+y]) return false;   
                }
            }
        }
        return true;
    }
}