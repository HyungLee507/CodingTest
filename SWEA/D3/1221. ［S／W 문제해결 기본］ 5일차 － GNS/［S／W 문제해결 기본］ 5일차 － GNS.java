import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    enum ANum implements Comparable<ANum> {
        ZRO(0), ONE(1), TWO(2), THR(3), FOR(4), FIV(5), SIX(6), SVN(7), EGT(8), NIN(9);

        private int value;

        ANum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            int iterationCount = Integer.parseInt(st.nextToken());
            int[] count = new int[10];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < iterationCount; i++) {
                String number = st.nextToken();
                ANum aNum = ANum.valueOf(number);
                int value = aNum.getValue();
                count[value]++;
            }
            System.out.printf("#%d ", test_case);
            ANum[] name = ANum.values();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < count[i]; j++) {
                    System.out.printf("%s ", name[i]);
                }
            }
            System.out.println();
        }
    }
}

