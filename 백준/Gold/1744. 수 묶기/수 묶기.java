import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> positives = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> negatives = new PriorityQueue<>();
        int zeroCounts = 0;
        for (int i = 0; i < number; i++) {
            int value = Integer.parseInt(bf.readLine());
            if (value > 0) {
                positives.add(value);
            } else if (value < 0) {
                negatives.add(value);
            } else {
                zeroCounts++;
            }
        }

        int sum = 0;
        int posSize = positives.size();
        int negSize = negatives.size();
        for (int i = 0; i < posSize / 2; i++) {
            Integer positive1 = positives.remove();
            Integer positive2 = positives.remove();
            if (positive1 == 1 || positive2 == 1) {
                sum += (positive1 + positive2);
            } else {
                sum += (positive1 * positive2);
            }
        }
        if (positives.size() == 1) {
            sum += positives.remove();
        }
        for (int i = 0; i < negSize / 2; i++) {
            Integer negative1 = negatives.remove();
            Integer negative2 = negatives.remove();
            sum += (negative2 * negative1);
        }
        if (negatives.size() == 1 && zeroCounts == 0) {
            sum += negatives.remove();
        }
        System.out.println(sum);


    }

}