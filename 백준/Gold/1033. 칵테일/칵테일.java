import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int ingredientCount = Integer.parseInt(bf.readLine());

        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < ingredientCount; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.addGroupOne(i);
            ingredients.add(ingredient);
        }
        for (int i = 0; i < ingredientCount - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int index1 = Integer.parseInt(st.nextToken());
            int index2 = Integer.parseInt(st.nextToken());
            int denominator = Integer.parseInt(st.nextToken());
            int numerator = Integer.parseInt(st.nextToken());
            int gsd = useEuclidean(denominator, numerator);
            denominator = denominator / gsd;
            numerator = numerator / gsd;

            int num1 = ingredients.get(index1).getValue() * numerator;
            int num2 = ingredients.get(index2).getValue() * denominator;
            gsd = useEuclidean(num1, num2);
            num1 /= gsd;
            num2 /= gsd;

            HashSet<Integer> group1 = ingredients.get(index1).getGroups();
            HashSet<Integer> group2 = ingredients.get(index2).getGroups();
            for (Integer index : group1) {
                ingredients.get(index).multiplyValue(num2);
                ingredients.get(index).addGroup(group2);
            }
            for (Integer index : group2) {
                ingredients.get(index).multiplyValue(num1);
                ingredients.get(index).addGroup(group1);
            }
//            ingredients.get(index1).addGroup(group2);
//            ingredients.get(index2).addGroup(group1);
        }
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.getValue()).append(' ');
        }
        System.out.println(sb);
    }


    static class Ingredient {
        int value;
        HashSet<Integer> groups = new HashSet<>();


        public Ingredient() {
            this.value = 1;
        }


        public void addGroupOne(int i) {
            this.groups.add(i);
        }

        public HashSet<Integer> getGroups() {
            return new HashSet<>(groups);
        }

        public void multiplyValue(int mul) {
            value = value * mul;
        }

        public int getValue() {
            return value;
        }

        public void addGroup(HashSet<Integer> group) {
            groups.addAll(group);
        }


    }

    private static int useEuclidean(int num1, int num2) {

        int max = Math.max(num1, num2);
        int min = Math.min(num1, num2);
        int number = max % min;
        if (number == 0) {
            return min;
        }
        return useEuclidean(number, min);
    }


}