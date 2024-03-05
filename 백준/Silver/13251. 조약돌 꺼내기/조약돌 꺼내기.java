import java.util.Scanner;

public class Main {
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        int M = s.nextInt(); // 조약돌 색상 수
        int[] stones = new int[M]; //각 색상별 조약돌 수
        int totalCount = 0; // 조약돌의 총 갯수

        for (int i = 0; i < M; i++) {
            stones[i] = s.nextInt();
            totalCount += stones[i];
        }

        int K = s.nextInt(); // 몇 번 뽑을지 선택

        // 가능한 모든 경우의 수 구하기
        double totalSituation = 1;
        for (int i = 0; i < K; i++) {
            totalSituation *= (totalCount - i);
        }

        // 같은 색상이 나오는 경우의 수 구하기
        double sum = 0;
        for (int i = 0; i < M; i++) {
            // 애초에 목표한 돌의 갯수보다 수가 작으면 SKIP
            if (stones[i] < K) {
                continue;
            }

            // 아니면 경우의 수 구해서 합하기
            double tmp = 1;
            for (int j = 0; j < K; j++) {
                tmp *= (stones[i] - j);
            }
            sum += tmp;
        }

        System.out.println(sum / totalSituation);
    }
}