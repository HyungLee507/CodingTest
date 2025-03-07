
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree, arr;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];
        int H = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        tree = new long[1 << (H + 1)];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }
        init(1, 1, n);
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switch (a) {
                case 1:
                    long val = Long.parseLong(st.nextToken());
                    update(1, 1, n, b, val);
                    break;
                case 2:
                    int c = Integer.parseInt(st.nextToken());
                    sb.append(get(1, 1, n, b, c)).append('\n');
                    break;
            }
        }
        System.out.println(sb);
        bf.close();
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        init(2 * node, start, mid);
        init(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    private static long get(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        long lsum = get(2 * node, start, mid, left, right);
        long rsum = get(2 * node + 1, mid + 1, end, left, right);
        return lsum + rsum;
    }

    private static void updateTree(int node, int start, int end, int index, long diff) {
        if (index < start || end < index) {
            return;
        }
        if (start == end) {
            tree[node] += diff;
            return;
        }
        int mid = (start + end) / 2;
        updateTree(2 * node, start, mid, index, diff);
        updateTree(2 * node + 1, mid + 1, end, index, diff);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    private static void update(int node, int start, int end, int index, long val) {
        long diff = (val - arr[index]);
        arr[index] = val;
        updateTree(node, start, end, index, diff);
    }
}
