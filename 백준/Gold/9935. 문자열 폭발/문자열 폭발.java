import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str1 = bf.readLine();
        String str2 = bf.readLine();
        int idx = 0;
        char last = str2.charAt(str2.length() - 1);
        StringBuilder sb = new StringBuilder();
        while (idx < str1.length()) {
            sb.append(str1.charAt(idx));
            if (str1.charAt(idx) == last) {
//                String substring = sb.substring(sb.length() - str2.length(), sb.length());
//                System.out.println(substring);
                if (sb.length() >= str2.length() && sb.substring(sb.length() - str2.length(), sb.length())
                        .equals(str2)) {
                    sb.delete(sb.length() - str2.length(), sb.length());
                }
            }
            idx++;
        }
        System.out.println(sb.toString().length() == 0 ? "FRULA" : sb.toString());
    }
}
