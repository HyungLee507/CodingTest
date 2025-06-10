import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] parent;
    static Rect[] rects;

    static class Rect {
        int x1, y1, x2, y2;

        public Rect(int x1, int y1, int x2, int y2) {
            this.x1 = Math.min(x1, x2);
            this.y1 = Math.min(y1, y2);
            this.x2 = Math.max(x1, x2);
            this.y2 = Math.max(y1, y2);
        }

        public boolean isConnected(Rect o) {
            if ((this.x1 < o.x1 && o.x2 < this.x2 && this.y1 < o.y1 && o.y2 < this.y2) ||
                    (this.x1 > o.x1 && o.x2 > this.x2 && this.y1 > o.y1 && o.y2 > this.y2)) {
                return false;
            }
            if (this.x2 < o.x1 || o.x2 < this.x1 || this.y2 < o.y1 || o.y2 < this.y1) {
                return false;
            }
            return true;
        }

        public boolean isOnBoundary(int x, int y) {
            boolean onXEdge = (x == x1 || x == x2) && (y1 <= y && y <= y2);
            boolean onYEdge = (y == y1 || y == y2) && (x1 <= x && x <= x2);
            return onXEdge || onYEdge;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        rects = new Rect[N];
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            rects[i] = new Rect(x1, y1, x2, y2);
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (rects[i].isConnected(rects[j])) {
                    union(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(find(i));
        }

        for (int i = 0; i < N; i++) {
            if (rects[i].isOnBoundary(0, 0)) {
                set.remove(find(i));
                break;
            }
        }

        System.out.println(set.size());
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            parent[ry] = rx;
        }
    }
}
