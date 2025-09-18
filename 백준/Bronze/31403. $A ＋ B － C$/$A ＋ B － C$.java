import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        int numericResult = A + B - C;

        String concat = String.valueOf(A) + String.valueOf(B); 
        int stringResult = Integer.parseInt(concat) - C;

        System.out.println(numericResult);
        System.out.println(stringResult);
    }
}
