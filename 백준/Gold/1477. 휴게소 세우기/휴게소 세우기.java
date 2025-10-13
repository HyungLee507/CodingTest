import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] rest = new int[N + 2]; 
        rest[0] = 0;       
        rest[N + 1] = L;  

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                rest[i] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(rest);

        int left = 0;       
        int right = L;     
        int mid;

        while (left + 1 < right) {
            mid = (left + right) / 2;

            if (canBuild(rest, M, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(right);
    }
    private static boolean canBuild(int[] rest, int M, int mid) {
        int need = 0;
        for (int i = 1; i < rest.length; i++) {
            int gap = rest[i] - rest[i - 1];
            if (gap > mid) {
                need += (gap - 1) / mid; 
                if (need > M) return false; 
            }
        }
        return need <= M;
    }
}
