import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        int sum = 0;

        for (int i = 0; i < numberCount; i++) {
            numbers.add(Integer.parseInt(bf.readLine()));
        }

        while (numbers.size() > 1) {

            int num1 = numbers.remove();
            int num2 = numbers.remove();
            sum += num1 + num2;
            numbers.add(num1 + num2);
        }
        System.out.println(sum);
    }


}