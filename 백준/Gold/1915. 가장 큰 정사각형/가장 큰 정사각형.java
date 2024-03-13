import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        int[][] map = new int[row + 1][column + 1];

        int maxLength = 0;

        for (int i = 1; i < row + 1; i++) {
            String temp = bf.readLine();
            for (int j = 1; j < column + 1; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j - 1)));
                if (map[i][j] == 1) {
                    map[i][j] = Math.min(map[i - 1][j - 1], Math.min(map[i][j - 1], map[i - 1][j])) + 1;
                }
                if (maxLength < map[i][j]) {
                    maxLength = map[i][j];
                }
            }
        }
        System.out.println(maxLength * maxLength);


    }


}