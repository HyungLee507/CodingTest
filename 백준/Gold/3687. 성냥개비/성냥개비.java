
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int min, max;
    static String[] minDp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());

        minDp = new String[101];
        // dp 테이블 갱신
        minDp[2] = "1";
        minDp[3] = "7";
        minDp[4] = "4";
        minDp[5] = "2";
        minDp[6] = "0";
        minDp[7] = "8";
        sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append(1);
        }
        String maxValue = sb.toString();
        String str = "11111";
        str.compareTo("11112");
        for (int i = 8; i <= 100; i++) {
            minDp[i] = String.valueOf(maxValue);
        }
        // TODO : 여기서부턴 이제 dp 테이블 갱신이네 + 근데 이제 8부턴 minDp[6] 은

        for (int i = 8; i <= 100; i++) {
            for (int j = 2; j <= i - 2; j++) {
                String left = (j == 6 ? "6" : minDp[j]);
                String candidate = left + minDp[i - j];
                if (isSmall(minDp[i], candidate)) {
                    minDp[i] = candidate;
                }
            }
        }
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int cnt = Integer.parseInt(bf.readLine());
            if (cnt == 6) {
                sb.append("6").append(' ');
            } else {
                sb.append(minDp[cnt]).append(' ');
            }
            sb.append(getMaxValue(cnt)).append('\n');
        }
        System.out.println(sb);
    }

    private static boolean isSmall(String comp, String candidate) {
        if (candidate.length() > 1 && candidate.charAt(0) == '0') {
            return false;
        }
        if (comp.length() != candidate.length()) {
            return candidate.length() < comp.length();
        }
        for (int i = 0; i < comp.length(); i++) {
            if (candidate.charAt(i) < comp.charAt(i)) {
                return true;
            } else if (candidate.charAt(i) > comp.charAt(i)) {
                return false;
            }
        }
        return false;
    }


    private static String getMaxValue(int cnt) {
        int size = cnt / 2;
        StringBuilder sb = new StringBuilder();
        if (cnt % 2 == 0) {
            for (int i = 0; i < size; i++) {
                sb.append(1);
            }
        } else {
            sb.append(7);
            for (int i = 0; i < size - 1; i++) {
                sb.append(1);
            }
        }
        return sb.toString();
    }
}
