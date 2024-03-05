import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int mode = Integer.parseInt(st.nextToken());

        if (mode == 1) {
            long k = Long.parseLong(st.nextToken());
            printPermutation(N, k);
        }
        if (mode == 2) {
            LinkedList<Integer> numbers = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            getNPermutation(N, numbers);
        }


    }

    private static void getNPermutation(int n, LinkedList<Integer> numbers) {
        long k = 1;
        int iterationCount = n;
        LinkedList<Integer> compareNumbers = IntStream.range(1, n + 1).boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        for (int i = 0; i < iterationCount - 1; i++) {
//            if (compareNumbers.getFirst() > numbers.getFirst()) {
//                k = k + (compareNumbers.indexOf(numbers.getFirst())) * factorial(n - 1);
//            } else if (compareNumbers.getFirst() < numbers.getFirst()) {
//                k = k + (compareNumbers.indexOf(numbers.getFirst())) * factorial(n - 1);
//            }
            k = k + (compareNumbers.indexOf(numbers.getFirst())) * factorial(n - 1);
            n -= 1;
            compareNumbers.remove(numbers.removeFirst());
        }
        System.out.println(k);
    }

    private static void printPermutation(int n, long k) {
        LinkedList<Integer> numbers = IntStream.range(1, n + 1).boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        StringBuilder sb = new StringBuilder();
        while (n >= 1) {
            long temp = factorial(n - 1);
            n -= 1;
            if (k == 0) {
                sb.append(numbers.removeLast()).append(" ");
                continue;
            }

            if (k - temp > 0) {
                int index = (int) (k / temp);
                if (k % temp == 0) {
                    sb.append(numbers.remove(index - 1)).append(" ");
                } else {
                    sb.append(numbers.remove(index)).append(" ");
                }
            } else {
                sb.append(numbers.removeFirst()).append(" ");
            }
            k = k % temp;
        }
        System.out.println(sb);
    }

    private static long factorial(int i) {
        if (i == 1 || i == 0) {
            return 1;
        }
        return i * factorial(i - 1);
    }
}