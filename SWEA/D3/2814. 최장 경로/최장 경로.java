import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] adjustList;
    static boolean[] visited;
	static int maxLen; 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            maxLen = 0;
            int nodeCount = sc.nextInt();
            int edgeCount = sc.nextInt();
            adjustList = new ArrayList[nodeCount+1];
            visited = new boolean[nodeCount+1];
            for(int i=1; i<= nodeCount; i++){
                adjustList[i] = new ArrayList<>();
            }
            for (int i = 0; i < edgeCount; i++) {
                int node1 = sc.nextInt();
                int node2 = sc.nextInt();
				adjustList[node1].add(node2);
                adjustList[node2].add(node1);
            }
            for(int i=1; i<= nodeCount;i++){
                dfs(i,1);                
            }
            // 끝 노드를 알면 되는데 이걸 어떻게 하더라 
            System.out.printf("#%d %d\n", test_case, maxLen);
        }
    }
    private static void dfs(int index ,int depth){
		maxLen = Math.max(depth , maxLen);
        List<Integer> adjustNodes = adjustList[index];
        visited[index] = true;
        for(int next : adjustNodes){
            if(!visited[next]){
                dfs(next, depth+1);
            }
        }
        visited[index] = false;
    }
}