import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        Stack<Integer> myStack = new Stack<>();
//        List<Integer> sequence = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(bf.readLine());
//        for (int i = 0; i < size; i++) {
//            sequence.add(Integer.parseInt(bf.readLine()));
//        }
        int temp = 0;
        for (int i = 0; i < size; i++) {
//            if (sequence.get(i) >= temp) {
            int i1 = Integer.parseInt(bf.readLine());
            if (i1 >= temp) {
                for (int j = temp; j < i1; j++) {
                    sb.append("+\n");
                    myStack.push(j + 1);
                }
                temp = i1;
                myStack.pop();
                sb.append("-\n");
//            } else if (sequence.get(i) < temp && myStack.peek().equals(sequence.get(i))) {
            } else if (i1 < temp && myStack.peek().equals(i1)) {
                myStack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);

    }
}