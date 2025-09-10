import java.io.*;
import java.util.*;

public class Main {
    static class P {
        int w, h;
        P(int w, int h) { this.w = w; this.h = h; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        P[] a = new P[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            a[i] = new P(w, h);
        }

        for (int i = 0; i < N; i++) {
            int rank = 1; 
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (a[j].w > a[i].w && a[j].h > a[i].h) rank++;
            }
            sb.append(rank).append(i + 1 == N ? '\n' : ' ');
        }

        System.out.print(sb.toString());
    }
}
