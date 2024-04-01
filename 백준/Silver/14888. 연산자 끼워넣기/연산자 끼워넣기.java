import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int PLUS = 0;
    static final int MINUS = 1;
    static final int MULTIPLY = 2;
    static final int DIVIDE = 3;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static List<Integer> numbers = new ArrayList<>();
    static int[] calculation = new int[4];

    public static void main(String[] args) throws IOException {
        initData();
        int startValue = numbers.get(0);
        getValue(startValue, 0, calculation);
        System.out.println(max);
        System.out.println(min);
    }

    private static void getValue(int value, int level, int[] calculations) {
        if (level == numbers.size() - 1) {
            max = Integer.max(max, value);
            min = Integer.min(min, value);
            return;
        }
        int num1 = numbers.get(level + 1);
        if (calculations[PLUS] >= 1) {
            getValue(value + num1, level + 1,
                    new int[]{calculations[0] - 1, calculations[1], calculations[2], calculations[3]});
        }
        if (calculations[MINUS] >= 1) {
            getValue(value - num1, level + 1,
                    new int[]{calculations[0], calculations[1] - 1, calculations[2], calculations[3]});
        }
        if (calculations[MULTIPLY] >= 1) {
            getValue(value * num1, level + 1,
                    new int[]{calculations[0], calculations[1], calculations[2] - 1, calculations[3]});
        }
        if (calculations[DIVIDE] >= 1) {
            getValue(value / num1, level + 1,
                    new int[]{calculations[0], calculations[1], calculations[2], calculations[3] - 1});
        }
    }

    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < numberCount; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            calculation[i] = (Integer.parseInt(st.nextToken()));
        }
    }
}