import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int[] arr;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = Arrays.stream(arr).max().orElse(-1);
        int min = 0;
        int answer = max;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (check(mid)) {
                answer = Math.min(answer, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(answer);
    }

    // mid 는 구간의 점수(문제에서 요구하는)를 나타냄.
    // 구간의 개수를 세서 구간의 점수를 바꿔가기 위한 검증 boolean return 메서드
    // 만약 mid (구간의 점수)에 따른 cnt 구간의 개수를 확인하고 이에 따른 true, false return

    // upper lower 바운드 설정을 어떻게 할지도 고려를 해야 됨.
    private static boolean check(int mid) {
        int cnt = 1;
        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
            if (max - min > mid) {
                cnt++;
                //
                min = arr[i];
                max = arr[i];
            }
        }
        return cnt <= m;
    }
}


