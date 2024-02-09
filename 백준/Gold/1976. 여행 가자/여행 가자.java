import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cities = Integer.parseInt(bf.readLine());
        parents = IntStream.range(0, cities + 1).toArray();
        int planCityCount = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= cities; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= cities; j++) {
                int isConnected = Integer.parseInt(st.nextToken());
                if (i < j && isConnected == 1) {
                    // 이부분 수정하면 될듯한데
//                    parents[j] = i;
//                    parents[j] = find(i);
                    if (find(i) < parents[j]) {
                        parents[find(j)] = find(i);
                    } else {
                        parents[find(i)] = find(j);
                    }

                }
            }
        }
        int[] planCities = new int[planCityCount];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < planCityCount; i++) {
            planCities[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < planCities.length - 1; i++) {
            if (find(planCities[i]) != find(planCities[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");


    }

    private static int find(int value) {
        if (value == parents[value]) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }
}