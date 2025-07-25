import java.io.*;
import java.util.*;

public class Main {
    static final int ALPH = 26;

    // 색상 트라이 풀
    static int[][] cNext;
    static boolean[] cEnd;
    static int cCnt = 1;

    // 닉네임 HashSet (역순으로 저장)
    static Set<String> nameRevSet = new HashSet<>();

    // 색상 삽입
    static void insertColor(String s) {
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (cNext[cur][idx] == 0)
                cNext[cur][idx] = cCnt++;
            cur = cNext[cur][idx];
        }
        cEnd[cur] = true;
    }

    // 닉네임을 역순으로 저장
    static void insertNameRev(String s) {
        StringBuilder sb = new StringBuilder(s);
        nameRevSet.add(sb.reverse().toString());
    }

    // 팀명이 “색상+닉네임”인지 검사
    static boolean isLegendary(String team) {
        int L = team.length();
        char[] A = team.toCharArray();
        int curC = 0;

        for (int i = 0; i < L; i++) {
            int ci = A[i] - 'a';
            if (cNext[curC][ci] == 0) break;
            curC = cNext[curC][ci];

            if (cEnd[curC]) {
                String suffix = team.substring(i + 1);
                String revSuffix = new StringBuilder(suffix).reverse().toString();
                if (nameRevSet.contains(revSuffix)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1) 입력 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 2) 색상 & 닉네임 전체 길이 합 계산
        String[] colors = new String[C];
        int sumColorLen = 0;
        for (int i = 0; i < C; i++) {
            colors[i] = br.readLine().trim();
            sumColorLen += colors[i].length();
        }
        String[] names = new String[N];
        for (int i = 0; i < N; i++) {
            names[i] = br.readLine().trim();
        }

        // 3) 색상 트라이 초기화
        cNext = new int[sumColorLen + 1][ALPH];
        cEnd  = new boolean[sumColorLen + 1];

        // 4) 색상·닉네임 삽입
        for (String s : colors) insertColor(s);
        for (String s : names)  insertNameRev(s);

        // 5) 쿼리 처리
        int Q = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            String team = br.readLine().trim();
            sb.append(isLegendary(team) ? "Yes\n" : "No\n");
        }
        System.out.print(sb);
    }
}
