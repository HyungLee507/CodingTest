import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    private static class Grade implements Comparable<Grade> {
        int score;
        int count;
        int index;

        public Grade(int score, int count, int index) {
            this.score = score;
            this.count = count;
            this.index = index;
        }

        @Override
        public int compareTo(Grade other) {
            if (this.score != other.score) {
                return Integer.compare(other.score, this.score);
            }
            if (this.count != other.count) {
                return Integer.compare(other.count, this.count);
            }
            return Integer.compare(this.index, other.index);
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T;
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int[][] answer = new int[n + 1][t + 1];
            int[] scores = new int[t + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j <= t; j++) {
                    answer[i][j] = Integer.parseInt(st.nextToken());
                    if (answer[i][j] == 0) {
                        scores[j]++;
                    }
                }
            }
            List<Grade> grades = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                int solves = 0;
                int totalScore = 0;
                for (int i = 1; i <= t; i++) {
                    if (answer[j][i] == 1) {
                        totalScore += scores[i];
                        solves++;
                    }
                }
                grades.add(new Grade(totalScore, solves, j));
            }

            Collections.sort(grades);
            int ranking = 1;
            int totalScore = 0;
            for (Grade temp : grades) {
                if (temp.index == p) {
                    totalScore = temp.score;
                    break;
                }
                ranking++;
            }
            System.out.printf("#%d %d %d\n", test_case, totalScore, ranking);
        }
    }
}