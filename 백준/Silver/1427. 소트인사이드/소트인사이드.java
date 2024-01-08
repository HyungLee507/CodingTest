import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        StringBuilder sb = new StringBuilder();
        List<Integer> numbers = line.chars().mapToObj(Character::getNumericValue).sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        for (Integer number : numbers) {
            sb.append(number);
        }
        System.out.println(sb);

    }
}