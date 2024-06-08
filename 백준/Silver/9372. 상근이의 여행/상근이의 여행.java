import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(bf.readLine());
        for (int i = 0; i < t; i++) {
            int count = 1;
            int answer = 0;
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            init(n);
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (count == n) {
                    continue;
                }
                if (parent[a] != parent[b]) {
                    count++;
                    answer++;
                    union(a, b);
                }

            }
            System.out.println(answer);
        }
    }

    private static void init(int n) {
        parent = new int[n + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x != y) {
            parent[y] = x;
        }
    }

    // 경로 압축 방법
    private static int find(int k) {
        if (parent[k] == k) {
            return k;
        }
        return parent[k] = find(parent[k]);
    }
}


