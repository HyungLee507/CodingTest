import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) { 
            System.out.println(0);
            return;
        }
        int n = Integer.parseInt(s.trim());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(a);

        int cut = (int) Math.round(n * 0.15); 
        int start = cut;
        int end = n - cut; 

        long sum = 0;
        for (int i = start; i < end; i++) sum += a[i];

        int cnt = end - start; 
        int ans = (int) Math.round((double) sum / cnt);
        System.out.println(ans);
    }
}
