import java.util.*;

class Solution {
    
    int totalDiff;
    int[] score;
    int numbers;
    int[] apache;
    
    public int[] solution(int n, int[] info) {
        score = new int[11];
        totalDiff = Integer.MIN_VALUE;
        apache = new int[11];
        numbers = n;
        for (int i = 0; i < info.length; i++) {
            apache[i] = info[i];
        }
        int[] scores = new int[11];
        for (int i = 0; i < info.length; i++) {
            dfs(0, 0, scores);
        }
        if (totalDiff <= 0) {
            int[] none = {-1};
            return none;
        } else {
            return score;
        }
        
    }
    
    private void dfs(int depth, int shoot, int[] scores) {
        if (depth == 11 || Arrays.stream(scores).sum() == numbers) {
            int total = getTotalDiff(scores);
            if (totalDiff < total) {
                totalDiff = total;
                score = Arrays.copyOf(scores, scores.length);
                return;
            }
            if (totalDiff == total) {
                compareScore(scores);
            }
            return;
        }
        int addShoot = apache[depth] + 1;
        if (shoot + addShoot > numbers) {
            addShoot = (numbers - shoot);
        }
        scores[depth] += addShoot;
        dfs(depth + 1, shoot + addShoot, scores);
        // 현재 점수에 화살 쏘는 경우
        // 값 원상복귀 해주고
        scores[depth] -= addShoot;
        // 현재 점수 화살 안쏘는 경우
        dfs(depth + 1, shoot, scores);

    }

    private void compareScore(int[] scores) {
        int first1 = 0;
        int first2 = 0;
        for (int i = scores.length - 1; i >= 0; i--) {
            if (score[i] != 0) {
                first1 = i;
                break;
            }
        }
        for (int i = scores.length - 1; i >= 0; i--) {
            if (scores[i] != 0) {
                first2 = i;
                break;
            }
        }
        if (first1 < first2) {
            score = Arrays.copyOf(scores, scores.length);
        }
    }

    private int getTotalDiff(int[] scores) {
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            if (apache[i] < scores[i]) {
                total += (10 - i);
            }
            if (apache[i] >= scores[i] && apache[i] != 0) {
                total -= (10 - i);
            }
        }
        return total;
    }
}