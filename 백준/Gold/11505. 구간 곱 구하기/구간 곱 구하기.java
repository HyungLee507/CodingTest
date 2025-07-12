import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final long MOD = 1_000_000_007;
    static long[] tree;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int h = 1;
        while ((1 << h) < N) h++;
        size = 1 << (h + 1);
        tree = new long[size];

        build(1, 1, N, bf);  // 재귀로 트리 초기화

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                update(1, 1, N, a, b);
            } else if (cmd == 2) {
                sb.append(query(1, 1, N, a, (int) b)).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void build(int node, int start, int end, BufferedReader bf) throws IOException {
        if (start == end) {
            tree[node] = Long.parseLong(bf.readLine());
        } else {
            int mid = (start + end) / 2;
            build(node * 2, start, mid, bf);
            build(node * 2 + 1, mid + 1, end, bf);
            tree[node] = tree[node * 2] * tree[node * 2 + 1] % MOD;
        }
    }

    private static void update(int node, int start, int end, int idx, long val) {
        if (idx < start || idx > end) return;
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);
        tree[node] = tree[node * 2] * tree[node * 2 + 1] % MOD;
    }

    private static long query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 1; // 곱셈이므로 단위 원소 1
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        long l = query(node * 2, start, mid, left, right);
        long r = query(node * 2 + 1, mid + 1, end, left, right);
        return l * r % MOD;
    }
}
