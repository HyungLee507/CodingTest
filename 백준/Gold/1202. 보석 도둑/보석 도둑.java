
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] jewels;
    static int[] bagsWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new int[N][2];
        bagsWeight = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < K; i++) {
            bagsWeight[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(bagsWeight);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        long ans = 0L;
        int idx = 0;

        for (int c : bagsWeight) {
            while (idx < N && jewels[idx][0] <= c) {
                maxHeap.offer(jewels[idx][1]);
                idx++;
            }
            if (!maxHeap.isEmpty()) {
                ans += maxHeap.poll();
            }
        }
        System.out.println(ans);
    }
}
