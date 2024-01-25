import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int lectureCounts = Integer.parseInt(st.nextToken());
        int blueRays = Integer.parseInt(st.nextToken());

        int[] lectures = new int[lectureCounts];
        int maxValue = Integer.MIN_VALUE;
        int sum = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < lectureCounts; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            maxValue = Math.max(lectures[i], maxValue);
            sum += lectures[i];
        }

        System.out.println(getMinimumBLueRays(lectures, maxValue, sum, blueRays));
    }

    private static int getMinimumBLueRays(int[] lectures, int min, int max, int blueRays) {
        while (min <= max) {
            int mid = (min + max) / 2;

            int count = getBlueRayCount(lectures, mid);

            if (count > blueRays) {
                // 현재 블루레이가 작음 -> 블루레이를 더 크게 만들어야 됨.
                min = mid + 1;
            } else {
                // 현재 블루레이가 작거나 큼 -> 블루레이를 더 작게 만들어야 됨.
                max = mid - 1;
            }
        }
        return min;
    }

    private static int getBlueRayCount(int[] lectures, int mid) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < lectures.length; i++) {
            if (sum + lectures[i] > mid) {
                sum = 0;
                count++;
            }
            sum += lectures[i];
        }
        // 마지막 구역이 남아있는 경우
        if (sum != 0) {
            count++;
        }

        return count;
    }
}