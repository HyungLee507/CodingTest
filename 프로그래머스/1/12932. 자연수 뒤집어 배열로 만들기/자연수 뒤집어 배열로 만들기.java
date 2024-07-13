class Solution {
    public int[] solution(long n) {
        String str = String.valueOf(n);
        int[] arr = new int[str.length()];
        for (int i = str.length() - 1; i >= 0; i--) {
            arr[str.length() - 1 - i] =Character.digit(str.charAt(i), 10);
        }
        return arr;
    }
}