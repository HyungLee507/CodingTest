import java.util.*;

class Solution {
    
    Cell[][] table;
    
    private static class Cell{
        String value;
        int[] parent;
        public Cell(int i, int j){
            this.value = "";
            parent = new int[]{i,j};
        }
    }
     
    public String[] solution(String[] commands) {
        init();
        List<String> execution = new ArrayList<>();
        for (String command : commands){
            String[] split = command.split(" ");
            if(split[0].equals("PRINT")){
                int row = Integer.parseInt(split[1]) -1;
                int col = Integer.parseInt(split[2]) -1;
                int parentRow = table[row][col].parent[0];
                int parentCol = table[row][col].parent[1];
                Cell temp = table[parentRow][parentCol];
                String statement = temp.value;
                if(statement.equals("")){
                    execution.add("EMPTY");
                }else{
                    execution.add(statement);    
                }
            }
            if(split[0].equals("MERGE")){
                int x1 = Integer.parseInt(split[1]);
                int y1 = Integer.parseInt(split[2]);
                int x2 = Integer.parseInt(split[3]);
                int y2 = Integer.parseInt(split[4]);
                union(x1,y1,x2,y2);
            }
            if(split[0].equals("UNMERGE")){
                int row = Integer.parseInt(split[1]);
                int col = Integer.parseInt(split[2]);
                unmerge(row,col);
            }
            if(split[0].equals("UPDATE") && split.length==4){
                int row = Integer.parseInt(split[1]) -1;
                int col = Integer.parseInt(split[2]) -1;
                int parentRow = table[row][col].parent[0];
                int parentCol = table[row][col].parent[1];
                Cell temp = table[parentRow][parentCol];
                temp.value = String.valueOf(split[3]);
            }
            if(split[0].equals("UPDATE") && split.length==3){
                String before = String.valueOf(split[1]);
                for(int i=0; i<50; i++){
                    for (int j=0; j<50; j++){
                        if(table[i][j].value.equals(before)){
                            table[i][j].value = String.valueOf(split[2]);
                        }
                    }
                }
            }
        }
        return execution.toArray(new String[0]);
    }
    
    private void unmerge(int x, int y){
        int[] temp = find(x-1, y-1);
        table[x - 1][y - 1].parent[0] = x - 1;
        table[x - 1][y - 1].parent[1] = y - 1;
        table[x-1][y-1].value = String.valueOf(table[temp[0]][temp[1]].value);
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(i == x-1 && j == y-1){
                    continue;
                }
                if(table[i][j].parent[0] == temp[0] && table[i][j].parent[1] == temp[1]){
                    table[i][j].value = "";
                    table[i][j].parent[0] = i;
                    table[i][j].parent[1] = j;
                }
            }
        }
    }
    
    private void union(int x1, int y1, int x2, int y2){
        int[] temp1 = find(x1 - 1, y1 - 1);
        int[] temp2 = find(x2 - 1, y2 - 1);
        if (temp1[0] == temp2[0] && temp1[1] == temp2[1]) {
            return;
        }
        if (table[temp1[0]][temp1[1]].value.isEmpty() && !table[temp2[0]][temp2[1]].value.isEmpty()) {
            table[temp1[0]][temp1[1]].value = String.valueOf(table[temp2[0]][temp2[1]].value);
        }

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (i == temp2[0] && j == temp2[1]) {
                    continue;
                }
                int[] temp = find(i, j);
                if (temp[0] == temp2[0] && temp[1] == temp2[1]) {
                    table[i][j].parent[0] = temp1[0];
                    table[i][j].parent[1] = temp1[1];
                }
            }
        }
        table[temp2[0]][temp2[1]].parent[0] = temp1[0];
        table[temp2[0]][temp2[1]].parent[1] = temp1[1];
    }
    // find 에는 원래 인덱스 즉 0부터 시작하는 값이 들어옴 
    private int[] find(int x, int y){
        if(table[x][y].parent[0] == x && table[x][y].parent[1] == y){
            return new int[]{table[x][y].parent[0] , table[x][y].parent[1]};
        }
        return find(table[x][y].parent[0] , table[x][y].parent[1]);
        
    }
    
    private void init(){
        table = new Cell[50][50];
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                table[i][j] = new Cell(i,j);
            }
        }
    }
    
}