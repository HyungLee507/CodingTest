import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int squareSize = Integer.parseInt(st.nextToken());
        int iterationCount = Integer.parseInt(st.nextToken());

        int[][] square = new int[squareSize][squareSize];

        for (int i = 0; i < squareSize; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < squareSize; j++) {
                if (i == 0 && j == 0) {
                    square[i][j] = Integer.parseInt(st.nextToken());
                } else if (i == 0) {
                    square[i][j] = square[i][j - 1] + Integer.parseInt(st.nextToken());
                } else if (j == 0) {
                    square[i][j] = square[i - 1][j] + Integer.parseInt(st.nextToken());
                } else {
                    square[i][j] = square[i - 1][j] + square[i][j - 1] - square[i - 1][j - 1] + Integer.parseInt(
                            st.nextToken());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1, y1 = Integer.parseInt(st.nextToken()) - 1, x2 =
                    Integer.parseInt(
                            st.nextToken()) - 1, y2 = Integer.parseInt(st.nextToken()) - 1;
            if (x1 == 0 && y1 == 0) {
                sb.append(square[x2][y2]).append('\n');
            } else if (x1 == 0) {
                int sum = square[x2][y2] - square[x2][y1 - 1];
                sb.append(sum).append('\n');
            } else if (y1 == 0) {
                int sum = square[x2][y2] - square[x1 - 1][y2];
                sb.append(sum).append('\n');
            } else {
                int sum = square[x2][y2] + square[x1 - 1][y1 - 1] - square[x1 - 1][y2] - square[x2][y1 - 1];
                sb.append(sum).append('\n');
            }
        }
        System.out.println(sb);
    }


}