import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] house;
    static int routerCount, houseCount;

    public static void main(String[] args) throws IOException {
        initData();
        printMaxInterval();
    }

    private static void printMaxInterval() {
        int result = 0;
        int left = 0;
        int right = house[houseCount] - house[1];

        while (left <= right) {
            int cnt = 1;
            int cur = house[1];
            int mid = (right + left) / 2;

            for (int i = 2; i <= houseCount; i++) {
                if (house[i] - cur >= mid) {
                    cnt++;
                    cur = house[i];
                }
            }
            if (cnt >= routerCount) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }

    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        houseCount = Integer.parseInt(st.nextToken());
        routerCount = Integer.parseInt(st.nextToken());
        house = new int[houseCount + 1];
        for (int i = 1; i <= houseCount; i++) {
            house[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(house);
    }
}
