import java.util.*;
class Solution {
    int zeroCount = 0;
    int oneCount = 0;
    public int[] solution(int[][] arr) {
        int n = arr.length;
        compression(arr, n);
        return new int[]{zeroCount , oneCount};
    }
    private void compression(int[][] arr, int n){
        int std = arr[0][0];
        boolean flag = true;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j]!=std){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            if(std == 1){
                oneCount++;
            }else{
                zeroCount++;
            }
            return;
        }else{
            int[][][] temp = new int[4][n/2][n/2];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    int x = (i / (n/2)) * 2;
                    int y = j / (n/2);
                    int x2 = i % (n/2);
                    int y2 = j % (n/2);
                    temp[x+y][x2][y2] = arr[i][j];          
                }
            }
            for(int i=0; i<4; i++){
                compression(temp[i],n/2);
            }
        }
        
    }
}