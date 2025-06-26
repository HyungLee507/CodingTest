import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] wordMasks;
    static final int FULL_MASK = (1 << 26) - 1;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wordMasks = new int[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            wordMasks[i] = mask;
        }

        dfs(0, 0);
        System.out.println(result);
    }

    static void dfs(int index, int alphaMask) {
        // pruning: 남은 단어 다 써도 못 채우면 중단
        int remainingMask = 0;
        for (int i = index; i < N; i++) {
            remainingMask |= wordMasks[i];
        }
        if ((alphaMask | remainingMask) != FULL_MASK) return;

        if (index == N) {
            if (alphaMask == FULL_MASK) result++;
            return;
        }

        // 포함 O
        dfs(index + 1, alphaMask | wordMasks[index]);

        // 포함 X
        dfs(index + 1, alphaMask);
    }
}
