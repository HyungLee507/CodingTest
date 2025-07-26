import java.io.*;
import java.util.*;

public class Main {
    static int[] parent, candy;
    static int N, M, K;
    static Map<Integer, Integer> groupCandy = new HashMap<>();
    static Map<Integer, Integer> groupSize = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        candy = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            groupCandy.put(i, candy[i]);  
            groupSize.put(i, 1);          
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int[] dp = new int[K]; 

        for (int root : groupCandy.keySet()) {
            int weight = groupSize.get(root);   
            int value = groupCandy.get(root);   

            for (int j = K - 1; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[K - 1]); 
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (rootA > rootB) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;

        groupCandy.put(rootA, groupCandy.get(rootA) + groupCandy.get(rootB));
        groupSize.put(rootA, groupSize.get(rootA) + groupSize.get(rootB));

        groupCandy.remove(rootB);
        groupSize.remove(rootB);
    }
}
