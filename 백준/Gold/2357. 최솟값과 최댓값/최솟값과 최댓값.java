

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr, minSegTree, maxSegTree;
    static int N, M;
    public static final int MAX = 1, MIN = -1;
    static int min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        int treeSize = getTreeSize();
        minSegTree = new int[treeSize];
        maxSegTree = new int[treeSize];
        init(MAX, maxSegTree, 1, N, 1);
        init(MIN, minSegTree, 1, N, 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            min = 1_000_000_001;
            max = -1;
            get(MIN, minSegTree, 1, N, 1, a, b);
            get(MAX, maxSegTree, 1, N, 1, a, b);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static void init(int type, int[] segTree, int start, int end, int node) {
        if (start == end) {
            segTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            init(type, segTree, start, mid, node * 2);
            init(type, segTree, mid + 1, end, node * 2 + 1);
            if (type == MIN) {
                if (segTree[node * 2] < segTree[node * 2 + 1]) {
                    segTree[node] = segTree[node * 2];
                } else {
                    segTree[node] = segTree[node * 2 + 1];
                }
            } else {
                if (segTree[node * 2] > segTree[node * 2 + 1]) {
                    segTree[node] = segTree[node * 2];
                } else {
                    segTree[node] = segTree[node * 2 + 1];
                }
            }
        }

    }

    private static void get(int type, int[] segTree, int start, int end, int node, int left, int right) {
        if (left > end || right < start) return;
        if (left <= start && end <= right) {
            if (type == MIN) {
                min = Math.min(min, segTree[node]);
            } else {
                max = Math.max(max, segTree[node]);
            }
            return;
        }

        int mid = (start + end) / 2;
        get(type, segTree, start, mid, node * 2, left, right);
        get(type, segTree, mid + 1, end, node * 2 + 1, left, right);
    }

    private static int getTreeSize() {
        int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        return (int) Math.pow(2, height);
    }
}
