import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int number1 = Integer.parseInt(st.nextToken());
        int number2 = Integer.parseInt(st.nextToken());
        int maxNum = Integer.max(number1, number2);
        int[][] numbers = new int[maxNum + 1][maxNum + 1];
        numbers[0][0] = 1;
        for (int i = 1; i <= maxNum; i++) {
            numbers[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                numbers[i][j] = (numbers[i - 1][j] % 10007 + numbers[i - 1][j - 1] % 10007) % 10007;
            }
        }
        System.out.println(numbers[number1][number2]);
    }
}