import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] stat;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        stat = new int[N][N];

        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                stat[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1, 0, 0); // 0번은 스타트 고정

        System.out.println(result);
    }

    static void dfs(int cur, int selectBit, int startSum, int linkSum) {
        if (cur == N) {
            result = Math.min(result, Math.abs(startSum - linkSum));
            return;
        }

        int startTemp = 0;
        int linkTemp = 0;

        for (int i = 0; i < cur; i++) {
            if ((selectBit & (1 << i)) != 0) {
                startTemp += stat[cur][i] + stat[i][cur];
            } else {
                linkTemp += stat[cur][i] + stat[i][cur];
            }
        }

        // 스타트팀에 cur 넣음
        dfs(cur + 1, selectBit | (1 << cur), startSum + startTemp, linkSum);

        // 링크팀에 cur 넣음
        dfs(cur + 1, selectBit, startSum, linkSum + linkTemp);
    }
}
