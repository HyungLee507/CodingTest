import static java.util.Comparator.reverseOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int numberCount = Integer.parseInt(bf.readLine());
        List<Integer> aGroup = new ArrayList<>();
        List<Integer> bGroup = new ArrayList<>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < numberCount; i++) {
            aGroup.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < numberCount; i++) {
            bGroup.add(Integer.parseInt(st.nextToken()));
        }

        aGroup.sort(reverseOrder());
        List<Integer> sortedBGroup = bGroup.stream().sorted().collect(Collectors.toList());

        int minimumSum = 0;
        for (int i = 0; i < numberCount; i++) {
            minimumSum += sortedBGroup.get(i) * aGroup.get(i);
        }
        System.out.println(minimumSum);


    }


}