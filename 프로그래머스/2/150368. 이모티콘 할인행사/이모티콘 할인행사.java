import java.util.*;


class Solution {
    int serviceCount = 0;
    int totalPayment = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] payment = new int[users.length];
        pay(payment, 0 ,users, emoticons);
        return new int[]{serviceCount,totalPayment};
    }
    
    private void pay(int[] payment, int index, int[][] users, int[] emoticons) {
        if (index == emoticons.length) {
            for (int i = 0; i < payment.length; i++) {
                int[] result = calcTotalPayment(payment, users);
                if (result[0] > serviceCount) {
                    serviceCount = result[0];
                    totalPayment = result[1];
                } else if (result[0] == serviceCount && result[1] > totalPayment) {
                    totalPayment = result[1];
                }
            }
            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            int[] nextValue = Arrays.copyOf(payment, payment.length);
            for (int j = 0; j < users.length; j++) {
//                int[] nextValue = Arrays.copyOf(payment, payment.length);
                if (users[j][0] <= i) {
                    nextValue[j] = nextValue[j] + (emoticons[index] * (100 - i) / 100);
                }
//                pay(nextValue, index + 1, users, emoticons);
            }
            pay(nextValue, index + 1, users, emoticons);

        }
    }
    private int[] calcTotalPayment(int[] payment, int[][] users) {
        int totalPrice = 0;
        for (int price : payment) {
            totalPrice += price;
        }
        int subscribeCount = 0;
        for (int i = 0; i < users.length; i++) {
            if (payment[i] >= users[i][1]) {
                subscribeCount++;
                totalPrice -= payment[i];
            }
        }
        return new int[]{subscribeCount, totalPrice};
    }
}