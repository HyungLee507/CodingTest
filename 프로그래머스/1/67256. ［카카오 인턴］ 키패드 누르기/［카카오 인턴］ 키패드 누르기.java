import java.util.*;
class Solution {
    
    private static class SellPhone {
        List<Integer> leftNumbers = new ArrayList<>(List.of(1, 4, 7));
        List<Integer> rightNumbers = new ArrayList<>(List.of(3, 6, 9));
        int[] left;
        int[] right;
        String mainHand;
        StringBuilder result;

        public SellPhone(String mainHand) {
            left = new int[]{3, 0};
            right = new int[]{3, 2};
            result = new StringBuilder();
            this.mainHand = mainHand;
        }

        private void inputNumber(int number) {
            if (leftNumbers.contains(number)) {
                chooseLeft(number);
                return;
            }
            if (rightNumbers.contains(number)) {
                chooseRight(number);
                return;
            }
            chooseCloseSide(number);
        }

        private void chooseLeft(int number) {
            int[] numberIndex;
            if (number == 0) {
                numberIndex = new int[]{3, 1};
            } else {
                numberIndex = new int[]{(number - 1) / 3, (number - 1) % 3};
            }
            left = numberIndex;
            result.append("L");
        }

        private void chooseRight(int number) {
            int[] numberIndex;
            if (number == 0) {
                numberIndex = new int[]{3, 1};
            } else {
                numberIndex = new int[]{(number - 1) / 3, (number - 1) % 3};
            }
            right = numberIndex;
            result.append("R");
        }

        private void chooseCloseSide(int number) {
            if (closeLeft(number) == 1) {
                chooseLeft(number);
                return;
            }
            if (closeLeft(number) == -1) {
                chooseRight(number);
                return;
            }
            if (mainHand.equals("left")) {
                chooseLeft(number);
            } else {
                chooseRight(number);
            }
        }

        private int closeLeft(int number) {
            int[] numberIndex;
            if (number == 0) {
                numberIndex = new int[]{3, 1};
            } else {
                numberIndex = new int[]{(number - 1) / 3, (number - 1) % 3};
            }
            int leftCount = Math.abs(numberIndex[0] - left[0]) + Math.abs(numberIndex[1] - left[1]);
            int rightCount = Math.abs(numberIndex[0] - right[0]) + Math.abs(numberIndex[1] - right[1]);
            if (leftCount < rightCount) {
                return 1;
            }
            if (leftCount == rightCount) {
                return 0;
            }
            return -1;
        }

        public String getResult() {
            return result.toString();
        }
    }
    
    public String solution(int[] numbers, String hand) {
        SellPhone phone = new SellPhone(hand);
        for(int number : numbers){
            phone.inputNumber(number);
        }
        return phone.getResult();
    }
}