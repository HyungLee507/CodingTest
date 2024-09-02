import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L, D;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        boolean[] response = new boolean[(L + 5) * (N + 1) * D];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < L; j++) {
                response[(L + 5) * i + j] = true;
            }
        }
        int answer = 0;
        int idx = 0;
        while (true) {
            if (!response[idx * D]) {
                answer = idx * D;
                break;
            }
            idx++;
        }
        System.out.println(answer);

    }

}