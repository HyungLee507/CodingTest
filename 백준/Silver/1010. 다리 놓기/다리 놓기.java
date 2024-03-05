import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] bridges = new long[31][31];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < bridges.length; i++) {
            bridges[1][i] = i;
        }
        for (int i = 2; i < bridges.length; i++) {
            for (int j = i; j < bridges.length; j++) {
                bridges[i][j] = bridges[i - 1][j - 1] + bridges[i][j - 1];
            }
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int iterationCount = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append(bridges[N][M]).append('\n');
        }
        System.out.println(sb);
    }
}