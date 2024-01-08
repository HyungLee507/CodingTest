import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int maxNumber = Integer.parseInt(bf.readLine());
        Deque<Integer> myQueue = new ArrayDeque<>();

        Collections.addAll(myQueue, IntStream.range(1, maxNumber + 1).boxed().toArray(Integer[]::new));

        while (myQueue.size() > 1) {
            myQueue.removeFirst();
            myQueue.add(myQueue.removeFirst());
        }
        System.out.println(myQueue.peek());

    }
}