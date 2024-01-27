import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int coins = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());

        int[] coinTypes = new int[coins];

        for (int i = 0; i < coins; i++) {
            coinTypes[i] = Integer.parseInt(bf.readLine());
        }
        int coinCount = 0;
        int presentIndex = coinTypes.length - 1;
        while (sum > 0) {
            if (sum / coinTypes[presentIndex] > 0) {
                coinCount += sum / coinTypes[presentIndex];
                sum = sum % coinTypes[presentIndex];
            }
            presentIndex--;
        }
        System.out.println(coinCount);

    }


}