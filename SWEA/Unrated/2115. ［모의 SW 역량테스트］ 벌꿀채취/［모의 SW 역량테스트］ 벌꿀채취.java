
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C, answer;
    static int[][] map;
    static List<List<Integer>> honeyBar;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            honeyBar = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 각 행에서 벌꿀통 선택
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    List<Integer> temp = new ArrayList<>();
                    for (int k = j; k < j + M; k++) {
                        temp.add(map[i][k]);
                    }
                    honeyBar.add(temp);
                }
            }

            answer = 0;
            dfs(0, 0, new ArrayList<>());
            sb.append('#').append(testCase).append(' ').append(answer).append('\n');
        }
        bf.close();
        System.out.println(sb);
    }

    private static void dfs(int index, int count, List<Integer> indexInfo) {
        if (index > honeyBar.size()) {
            return;
        }

        if (count == 2) {
            int sum = getMaxValue(indexInfo);
            answer = Math.max(answer, sum);
            return;
        }

        indexInfo.add(index);
        dfs(index + 1, count + 1, indexInfo);
        indexInfo.remove(indexInfo.size() - 1);
        dfs(index + 1, count, indexInfo);
    }

    private static int getMaxValue(List<Integer> indexInfo) {
        int index1 = indexInfo.get(0);
        int index2 = indexInfo.get(1);

        int row1 = index1 / (N - M + 1);
        int col1 = index1 % (N - M + 1);
        int row2 = index2 / (N - M + 1);
        int col2 = index2 % (N - M + 1);
        
        if (row1 == row2 && Math.abs(col1 - col2) < M) {
            return 0;
        }
        int max1 = getMaxHoney(honeyBar.get(index1));
        int max2 = getMaxHoney(honeyBar.get(index2));
        return max1 + max2;
    }

    private static int getMaxHoney(List<Integer> honeyList) {
        int n = honeyList.size();
        int maxProfit = 0;

        // 부분집합을 통해 최대 수익 계산
        for (int subset = 0; subset < (1 << n); subset++) {
            int sum = 0;
            int profit = 0;
            for (int i = 0; i < n; i++) {
                if ((subset & (1 << i)) != 0) {
                    sum += honeyList.get(i);
                    profit += honeyList.get(i) * honeyList.get(i);
                }
            }
            if (sum <= C) {
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}
