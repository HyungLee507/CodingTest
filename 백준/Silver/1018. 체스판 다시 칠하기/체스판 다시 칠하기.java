import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        String[] board = new String[N];
        for (int i = 0; i < N; i++) board[i] = br.readLine();

        int answer = Integer.MAX_VALUE;

        for (int r = 0; r <= N - 8; r++) {
            for (int c = 0; c <= M - 8; c++) {
                int repaintIfStartW = 0;

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        char cur = board[r + i].charAt(c + j);
                        char expected = ((i + j) % 2 == 0) ? 'W' : 'B';
                        if (cur != expected) repaintIfStartW++;
                    }
                }

                int repaintIfStartB = 64 - repaintIfStartW;

                int localMin = Math.min(repaintIfStartW, repaintIfStartB);
                answer = Math.min(answer, localMin);
            }
        }

        System.out.println(answer);
    }
}
