import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] connection;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int peopleCount = Integer.parseInt(st.nextToken());
        int friendConnections = Integer.parseInt(st.nextToken());
        connection = new long[peopleCount + 1][peopleCount + 1];
        for (int i = 1; i < peopleCount; i++) {
            connection[i][i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < friendConnections; i++) {
            st = new StringTokenizer(bf.readLine());
            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken());
            connection[startIndex][endIndex] = 1;
            connection[endIndex][startIndex] = 1;
        }
        for (int i = 1; i <= peopleCount; i++) {
            for (int j = 1; j <= peopleCount; j++) {
                for (int k = 1; k <= peopleCount; k++) {
                    if (connection[j][k] == 0 && connection[j][i] != 0 && connection[i][k] != 0) {
                        connection[j][k] = connection[j][i] + connection[i][k];
                    }
                    if (connection[j][k] != 0 && connection[j][i] != 0 && connection[i][k] != 0
                            && connection[j][k] != Integer.MAX_VALUE) {
                        connection[j][k] = Long.min(connection[j][i] + connection[i][k], connection[j][k]);
                    }

                }
            }
        }
        int baconNumber = Integer.MAX_VALUE;
        int minimumNumberIndex = 0;
        for (int i = 1; i <= peopleCount; i++) {
            int minNumber = 0;
            for (int j = 1; j <= peopleCount; j++) {
                if (j == i) {
                    continue;
                }
                minNumber += connection[i][j];
            }
            if (baconNumber > minNumber) {
                baconNumber = minNumber;
                minimumNumberIndex = i;
            }
        }
        System.out.println(minimumNumberIndex);
    }
}