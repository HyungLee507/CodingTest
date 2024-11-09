

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int iterationCount = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            sb.append(binSearch(start, end)).append('\n');
        }
        System.out.println(sb);
        bf.close();
    }

    private static long binSearch(long start, long end) {
        long left = -1;
        long right = end - start + 1;
        long dist = end - start;
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (canArrived(mid, dist)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private static boolean canArrived(long target, long dist) {
        long result = 0;
        for (long l = target; l > 0; l -= 2) {
            result += l;
            if (result >= dist) return true;
        }
        return result >= dist;
    }
}
