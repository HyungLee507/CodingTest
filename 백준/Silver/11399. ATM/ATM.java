import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bf.readLine());
        int[] withdrawalTime = new int[size];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < size; i++) {
            withdrawalTime[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(withdrawalTime);

        for (int i = 1; i < size; i++) {
            withdrawalTime[i] = withdrawalTime[i - 1] + withdrawalTime[i];
        }
        System.out.println(Arrays.stream(withdrawalTime).sum());

    }
}