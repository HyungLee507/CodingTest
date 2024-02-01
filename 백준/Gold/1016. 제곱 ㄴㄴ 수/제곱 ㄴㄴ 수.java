import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        long searchBound = max - min + 1L;

        boolean[] isSquareNumber = new boolean[(int) searchBound];
        long notSquareNumbers = searchBound;

        for (long l = 2; l * l <= max; l++) {
            long powL = l * l;
            long start = (min / powL);
            if (min % powL != 0) {
                start += 1;
            }
            for (long j = start; j * powL <= max; j++) {
                if (!isSquareNumber[(int) (j * powL - min)]) {
                    isSquareNumber[(int) (j * powL - min)] = true;
                    notSquareNumbers--;
                }
            }


        }
        System.out.println(notSquareNumbers);


    }


}