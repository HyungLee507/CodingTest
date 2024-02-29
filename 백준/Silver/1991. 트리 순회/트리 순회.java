import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] tree;
    static final int LEFT_LEAF = 0;
    static final int RIGHT_LEAF = 1;
    static final int NOT_VALUE = 64;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nodeCount = Integer.parseInt(bf.readLine());

        tree = new int[nodeCount + 1][2];
        for (int i = 1; i <= nodeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int index = st.nextToken().charAt(0) - '@';
            int leftLeaf = st.nextToken().charAt(0) - '@';
            int rightLeaf = st.nextToken().charAt(0) - '@';
            if (leftLeaf >= 0) {
                tree[index][LEFT_LEAF] = leftLeaf;
            } else {
                tree[index][LEFT_LEAF] = 0;
            }
            if (rightLeaf >= 0) {
                tree[index][RIGHT_LEAF] = rightLeaf;
            } else {
                tree[index][RIGHT_LEAF] = 0;
            }
        }
        preOrder(1);
        sb.append('\n');
        inOrder(1);
        sb.append('\n');
        postOrder(1);
        sb.append('\n');
        System.out.println(sb);
    }

    public static void preOrder(int parent) {
        char value = (char) ((char) parent + '@');
        if (value == NOT_VALUE) {
            return;
        }
        sb.append(value);
        preOrder(tree[parent][LEFT_LEAF]);
        preOrder(tree[parent][RIGHT_LEAF]);
    }

    public static void inOrder(int parent) {
        char value = (char) ((char) parent + '@');
        if (value == NOT_VALUE) {
            return;
        }
        inOrder(tree[parent][LEFT_LEAF]);
        sb.append(value);
        inOrder(tree[parent][RIGHT_LEAF]);
    }

    public static void postOrder(int parent) {
        char value = (char) ((char) parent + '@');
        if (value == NOT_VALUE) {
            return;
        }
        postOrder(tree[parent][LEFT_LEAF]);
        postOrder(tree[parent][RIGHT_LEAF]);
        sb.append(value);
    }
}