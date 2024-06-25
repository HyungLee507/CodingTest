import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] sum = new int[n - k + 1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int temp = 0;
        for (int i = 0; i < k; i++) {
            temp += arr[i];
        }
        sum[0] = temp;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] - arr[i - 1] + arr[i + k - 1];
        }
        int max = Arrays.stream(sum).boxed().mapToInt((a) -> (int) a).max().orElse(-1);
        System.out.println(max);
    }
}
