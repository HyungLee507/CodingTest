import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
        }

        for (int i = 1; i < arrayB.length; i++) {
            gcdB = gcd(gcdB, arrayB[i]);
        }

        int answerA = canUse(gcdA, arrayB) ? gcdA : 0;
        int answerB = canUse(gcdB, arrayA) ? gcdB : 0;

        return Math.max(answerA, answerB);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private boolean canUse(int value, int[] array) {
        for (int num : array) {
            if (num % value == 0) {
                return false;
            }
        }

        return true;
    }
}