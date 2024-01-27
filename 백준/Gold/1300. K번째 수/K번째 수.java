import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int depth = Integer.parseInt(bf.readLine());
        int k = Integer.parseInt(bf.readLine());
        int start = 1;
        int end = k;
        int ans = 0;
        while (start <= end) {
            int count = 0;
            int mid = (start + end) / 2;
            for (int i = 1; i <= depth; i++) {
                count += Math.min(mid / i, depth);
            }

            if (count < k) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }

        }
        System.out.println(ans);
    }


}