
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long G = Long.parseLong(br.readLine());

        long left = 1;
        long right = 2;
        StringBuilder sb = new StringBuilder();
        boolean found = false;

        while (2 * right - 1 <= G) {
            long diff = right * right - left * left;

            if (diff == G) {
                sb.append(right).append('\n');
                found = true;
                right++;
            } else if (diff < G) {
                right++;
            } else {
                left++;
            }

            if (left == right) {
                right++;
            }
        }

        System.out.print(found ? sb.toString() : "-1");
    }
}
