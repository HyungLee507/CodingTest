

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] price;
    static Map<Integer, String> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(bf.readLine());
        price = new int[N];
        dp = new HashMap<>();
        st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
        String answer = dfs(0, "");
        System.out.println(answer.substring(answer.length() - len(answer)));
        bf.close();
    }

    static String dfs(int money, String str) {
        if (dp.containsKey(money)) return dp.get(money);
        String result = "";
        for (int i = 0; i < N; i++) {
            if (money + price[i] > M) continue;
            String newStr = dfs(money + price[i], str + i) + i;
            result = max(result, newStr);
        }
        dp.put(money, result);
        return result;
    }

    static String max(String str1, String str2) {
        int l1 = len(str1), l2 = len(str2);
        if (l1 > l2) return str1;
        if (l1 < l2) return str2;
        int s1 = str1.length() - l1;
        int s2 = str2.length() - l2;
        for (int i = 0; i < l1; i++) {
            char c1 = str1.charAt(s1 + i);
            char c2 = str2.charAt(s2 + i);
            if (c1 > c2) return str1;
            if (c1 < c2) return str2;
        }
        return str1;
    }

    static int len(String str) {
        int result = str.length();
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) != '0') break;
            result--;
        }
        return result;
    }
}
