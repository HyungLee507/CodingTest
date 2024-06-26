class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for(int i=0;i<answer.length; i++){
            for(int j=0; j<answer[0].length; j++){
                int temp =0;
                for(int z=0;z<arr1[0].length; z++){
                    temp += (arr1[i][z] * arr2[z][j]);
                }
                answer[i][j] = temp;
            }
        }
        return answer;
    }
}