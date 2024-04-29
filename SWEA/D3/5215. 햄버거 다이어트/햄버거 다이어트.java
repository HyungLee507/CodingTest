import java.util.Scanner;

class Solution {
    static boolean[] chosen;
    static Ingredient[] ingredients;
    static int totalTasteScore, goalCalorie;

    private static class Ingredient {
        int tasteScore;
        int calorie;

        public Ingredient(int tasteScore, int calorie) {
            this.tasteScore = tasteScore;
            this.calorie = calorie;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int ingredientCount = sc.nextInt();
            goalCalorie = sc.nextInt();
            ingredients = new Ingredient[ingredientCount];
            chosen = new boolean[ingredientCount];
            for (int i = 0; i < ingredientCount; i++) {
                ingredients[i] = new Ingredient(sc.nextInt(), sc.nextInt());
            }
            totalTasteScore = 0;
            dfs(0, 0, 0);
            System.out.printf("#%d %d\n", test_case, totalTasteScore);
        }

    }

    private static void dfs(int index, int calorie, int tasteScore) {
        if (index >= ingredients.length) {
            return;
        }
        if (ingredients[index].calorie + calorie <= goalCalorie) {
            totalTasteScore = Math.max(ingredients[index].tasteScore + tasteScore, totalTasteScore);
            chosen[index] = true;
            dfs(index + 1, ingredients[index].calorie + calorie, ingredients[index].tasteScore + tasteScore);
            chosen[index] = false;
        }
        dfs(index + 1, calorie, tasteScore);
    }

}