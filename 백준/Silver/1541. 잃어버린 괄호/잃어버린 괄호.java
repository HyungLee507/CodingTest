import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String mathExpression = bf.readLine();
        String[] split = mathExpression.split("-");
        long sum = 0L;
        int minusCount = split.length - 1;
        if (minusCount == 0) {
            System.out.println(plusExecution(mathExpression));
            return;
        }
        sum += plusExecution(split[0]);
        for (int i = 1; i < split.length; i++) {
            sum -= plusExecution(split[i]);
        }
        System.out.println(sum);
    }


    private static long plusExecution(String subExpression) {

        String[] split = subExpression.split("\\+");
        if (split.length == 1) {
            return Long.parseLong(subExpression);
        }
        long subSum = 0L;
        for (String line : split) {
            subSum += Long.parseLong(line);
        }
        return subSum;
    }
}