
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(bf.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(bf.readLine());
            if (n == 0) {
                deque.pollLast();
                continue;
            }
            deque.offer(n);
        }
        System.out.println(deque.stream().mapToInt(Integer::intValue).sum());
    }
}
