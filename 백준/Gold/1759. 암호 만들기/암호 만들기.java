
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static char[] chars;
    static int N, M;
    static TreeSet<String> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chars = new char[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars);
        ans = new TreeSet<>();
        dfs(0, 0, 0, 0, new StringBuilder());
        StringBuilder sb = new StringBuilder();
        for (String an : ans) {
            sb.append(an).append('\n');
        }
        System.out.println(sb);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static void dfs(int idx, int cnt, int v, int c, StringBuilder sb) {
        if (cnt == N) {
            if (v >= 1 && c >= 2) {
                ans.add(sb.toString());
            }
            return;
        }
        if (idx == M) {
            return;
        }

        char ch = chars[idx];
        sb.append(ch);
        if (isVowel(ch)) {
            dfs(idx + 1, cnt + 1, v + 1, c, sb);
        } else {
            dfs(idx + 1, cnt + 1, v, c + 1, sb);
        }
        sb.deleteCharAt(sb.length() - 1);

        dfs(idx + 1, cnt, v, c, sb);
    }


}
