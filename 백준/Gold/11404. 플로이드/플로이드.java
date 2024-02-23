import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cityCount = Integer.parseInt(bf.readLine());
        int busCount = Integer.parseInt(bf.readLine());
        map = new long[cityCount + 1][cityCount + 1];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }

        for (int i = 0; i < busCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (cost < map[startCity][endCity]) {
                map[startCity][endCity] = cost;
            }
        }
        for (int i = 1; i <= cityCount; i++) {
            for (int j = 1; j <= cityCount; j++) {
                for (int k = 1; k <= cityCount; k++) {
                    map[j][k] = Long.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= cityCount; i++) {
            for (int j = 1; j <= cityCount; j++) {
                if (map[i][j] == Integer.MAX_VALUE) {
                    sb.append("0 ");
                } else {
                    sb.append(map[i][j]).append(" ");

                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}