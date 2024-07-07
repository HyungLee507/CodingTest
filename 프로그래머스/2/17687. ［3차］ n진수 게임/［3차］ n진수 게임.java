import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        int number = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() / m <= t) {
            sb.append(Integer.toString(number, n));
            number++;
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            answer.append(sb.charAt(m * i + p - 1));
        }
        return answer.toString().toUpperCase(Locale.ROOT);
    }
}