import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 0 && b == 0 && c == 0) break;

            int[] sides = {a, b, c};
            Arrays.sort(sides); 

            if (sides[2] * sides[2] == sides[0] * sides[0] + sides[1] * sides[1]) {
                sb.append("right\n");
            } else {
                sb.append("wrong\n");
            }
        }

        System.out.print(sb);
    }
}
