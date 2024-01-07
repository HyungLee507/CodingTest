import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static final boolean push = true;
    public static final boolean pop = false;


    public static List<Boolean> instructions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Stack<Integer> myStack = new Stack<>();
        List<Integer> sequence = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bf.readLine());
        for (int i = 0; i < size; i++) {
            sequence.add(Integer.parseInt(bf.readLine()));
        }
        int temp = 0;
        for (int i = 0; i < size; i++) {
            if (sequence.get(i) >= temp) {
                for (int j = temp; j < sequence.get(i); j++) {
                    instructions.add(push);
                    myStack.push(j + 1);
                }
                temp = sequence.get(i);
                myStack.pop();
                instructions.add(pop);
            } else if (sequence.get(i) < temp && myStack.peek().equals(sequence.get(i))) {
                myStack.pop();
                instructions.add(pop);
            } else {
                System.out.println("NO");
                return;
            }
        }
        for (Boolean instruction : instructions) {
            if (instruction == push) {
                System.out.println("+");
            } else {
                System.out.println("-");
            }
        }


    }
}