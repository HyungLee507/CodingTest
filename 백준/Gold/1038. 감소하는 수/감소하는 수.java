import java.io.*;
import java.util.*;


public class Main {
    static List<Long> decs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int d = 0; d <= 9; d++) {
            dfs(d, d);  
        }

        Collections.sort(decs);

        if (N >= decs.size()) {
            System.out.println(-1);
        } else {
            System.out.println(decs.get(N));
        }
    }

    static void dfs(long num, int last) {
        decs.add(num); 

        for (int next = 0; next < last; next++) {
            dfs(num * 10 + next, next);
        }
    }
}
