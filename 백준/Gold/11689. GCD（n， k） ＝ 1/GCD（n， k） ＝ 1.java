import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long number = Long.parseLong(bf.readLine());
        long exclusiveNumbers = number;

        for (long l = 2L; l <= Math.sqrt(number); l++) {

            if (number % l == 0) {
                exclusiveNumbers = exclusiveNumbers - exclusiveNumbers / l;
            }
            while (number % l == 0) {
                number = number / l;
            }
        }
        if (number > 1) {
            exclusiveNumbers = exclusiveNumbers - exclusiveNumbers / number;
        }
        System.out.println(exclusiveNumbers);
    }


}