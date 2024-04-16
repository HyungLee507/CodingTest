import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i=0; i<numbers.length; i++){
            String temp = getBinNumber(numbers[i]);
            if(canMakeBinTree(0,temp.length()-1,temp)){
                answer[i] = 1;
            }
        }
        return answer;
    }
    private boolean canMakeBinTree(int left, int right, String binNumber) {

        Queue<int[]> subTrees = new LinkedList<>();
        if (left == right) {
            if (binNumber.equals("0")) {
                return false;
            }
            if (binNumber.equals("1")) {
                return true;
            }
        }
        subTrees.add(new int[]{left, right});
        while (!subTrees.isEmpty()) {
            int[] now = subTrees.poll();
            int now_left = now[0];
            int now_right = now[1];
            int now_mid = (now_left + now_right) / 2;
            if (now_mid - 1 != now_left || now_mid + 1 != now_right) {
                subTrees.add(new int[]{now_left, now_mid - 1});
                subTrees.add(new int[]{now_mid + 1, now_right});
            }
            int leftNodeIndex = (now_left + (now_mid - 1)) / 2;
            int rightNodeIndex = ((now_mid + 1) + now_right) / 2;
            if (binNumber.charAt(now_mid) == '0' && (binNumber.charAt(leftNodeIndex) == '1'
                    || binNumber.charAt(rightNodeIndex) == '1')) {
                return false;
            }
        }
        return true;
    }

    private  String getBinNumber(long number) {
        String temp = Long.toBinaryString(number);
        int binNumLength = getLengthDiff(number, temp.length());
        StringBuilder sb = new StringBuilder();
        while (binNumLength > 0) {
            sb.append('0');
            binNumLength--;
        }
        return sb.append(temp).toString();
    }

    private  int getLengthDiff(long number, int binLength) {

        int originalLength = 1;
        int i = 0;
        while (true) {
            if (number > Math.pow(2, Math.pow(2, i + 1) - 1) - 1) {
                originalLength += (int) Math.pow(2, i + 1);
                i++;
            } else {
                break;
            }
        }
        if (originalLength > binLength) {
            return originalLength - binLength;
        }

        return 0;
    }
    
}