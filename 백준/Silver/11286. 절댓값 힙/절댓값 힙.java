import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int instructionCount = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> myQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int absCompare = Integer.compare(Math.abs(o1), Math.abs(o2));
                if (absCompare == 0) {
                    return Integer.compare(o1, o2);
                }
                return absCompare;
            }
        });

        for (int i = 0; i < instructionCount; i++) {
            int number = Integer.parseInt(bf.readLine());
            if (number != 0) {
                myQueue.add(number);
                continue;
            }
            if (myQueue.isEmpty()) {
                System.out.println("0");
            } else {
                System.out.println(myQueue.remove());
            }
        }


    }
}