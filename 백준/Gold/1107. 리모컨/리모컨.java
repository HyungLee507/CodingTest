

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int initValue = 100;
    static String N;
    static int ans;
    static HashSet<Integer> brokenButton;
    static HashSet<Integer> numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = bf.readLine();
        int target = Integer.parseInt(N);
        brokenButton = new HashSet<>();
        int n = Integer.parseInt(bf.readLine());

        if (n > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                brokenButton.add(Integer.parseInt(st.nextToken()));
            }
        }

        numbers = new HashSet<>();
        ans = Math.abs(target - initValue);

        int len = N.length();

        for (int i = -1; i <= 1; i++) {
            int currentLen = len + i;
            if (currentLen > 0 && currentLen <= 6) {  // 길이 제한 추가
                createNumbers(0, currentLen, new StringBuilder());
            }
        }

        for (Integer number : numbers) {
            int press = String.valueOf(number).length();
            int move = Math.abs(number - target);
            ans = Math.min(ans, press + move);
        }

        System.out.println(ans);
    }

    private static void createNumbers(int depth, int len, StringBuilder sb) {
        if (depth == len) {
            if (len > 1 && sb.charAt(0) == '0') {
                return;
            }
            numbers.add(Integer.parseInt(sb.toString()));
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!brokenButton.contains(i)) {
                sb.append(i);
                createNumbers(depth + 1, len, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
