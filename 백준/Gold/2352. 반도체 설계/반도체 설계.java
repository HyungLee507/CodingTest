
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static List<Integer> lis;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        lis = new ArrayList<>();
        lis.add(arr[0]);
        for (int i = 1; i < N; i++) {
            int ele = arr[i];
            if (lis.get(lis.size() - 1) < ele) {
                lis.add(ele);
            } else {
                int searchedIdx = lowerBound(ele);
                lis.set(searchedIdx, ele);
            }
        }
        System.out.println(lis.size());
    }

    public static int lowerBound(int target) {
        int start = -1;
        int end = lis.size() + 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (lis.get(mid) <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }

}
