class Solution {
    public int solution(int n) {
        String third = Integer.toString(n,3);
        String reverse = getReverseNumber(third);
        int answer = Integer.parseInt(reverse, 3);
        return answer;
    }
    private String getReverseNumber(String third){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< third.length(); i++){
            sb.append(third.charAt(third.length()-1-i));
        }
        return sb.toString();
    }
}