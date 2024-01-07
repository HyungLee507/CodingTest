import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bf.readLine());
        int ingredientSum = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        List<Integer> ingredients = new ArrayList<>();

        while (st.hasMoreTokens()) {
            ingredients.add(Integer.parseInt(st.nextToken()));
        }
        int totalArmors = getTotalArmor(ingredients, ingredientSum);
        System.out.println(totalArmors);

    }

    private static int getTotalArmor(List<Integer> ingredients, int ingredientSum) {
        int totalArmors = 0;
        Collections.sort(ingredients);
        for (int i = 0; i < ingredients.size(); i++) {
            for (int j = i + 1; j < ingredients.size(); j++) {
                if (ingredients.get(j) + ingredients.get(i) == ingredientSum) {
                    totalArmors++;
                    break;
                }
            }
        }
        return totalArmors;
    }


}