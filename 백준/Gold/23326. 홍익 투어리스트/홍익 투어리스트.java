
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static TreeSet<Integer> place;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        place = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp == 1) {
                place.add(i + 1);
            }
        }
        int loc = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int query = Integer.parseInt(st.nextToken());
            if (query == 1) {
                int idx = Integer.parseInt(st.nextToken());
                changeHotPlace(idx);
            } else if (query == 2) {
                int cnt = Integer.parseInt(st.nextToken());
                loc = (loc + cnt - 1) % N + 1;
            } else if (query == 3) {
                sb.append(binSearch(loc)).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static void changeHotPlace(int idx) {
        if (place.contains((Integer) idx)) {
            place.remove((Integer) idx);
        } else {
            place.add(idx);
        }
    }

    private static int binSearch(int loc) {
        if (place.isEmpty()) {
            return -1;
        }
        if (place.contains(loc)) {
            return 0;
        }
        Integer res = place.ceiling(loc);
        if (res == null) {
            res = place.first();
            return (res + N - loc) % N;
        } else {
            return (res - loc + N) % N;
        }
    }
}
