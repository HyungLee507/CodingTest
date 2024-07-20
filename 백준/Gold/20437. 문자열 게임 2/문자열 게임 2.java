import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            String W = br.readLine();
            int inputLen = W.length();
            int K = Integer.parseInt(br.readLine());
            if (K == 1) {
                sb.append("1 1").append("\n");
                continue;
            }
            int [] alphaCount = new int[26];
            for (int i = 0; i < inputLen; i++) {
                char c = W.charAt(i);
                alphaCount[c - 97]++;
            }
            for (int i = 0; i <= inputLen - K; i++) {
                char start = W.charAt(i);
                if (alphaCount[start - 97] >= K) {
                    int tmpCount = 1;
                    for (int j = i + 1; j < inputLen; j++) {
                        char cur = W.charAt(j);
                        if (start == cur) {
                            tmpCount++;

                            if (tmpCount == K) {
                                int len = (j - i) + 1;
                                min = Math.min(min, len);
                                max = Math.max(max, len);
                                break;
                            }
                        }
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                sb.append("-1");
            }
            else {
                sb.append(min).append(" ").append(max);
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }
}