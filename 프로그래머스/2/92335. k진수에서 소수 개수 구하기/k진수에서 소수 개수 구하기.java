import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String kDemicalNumber = getDemicalNumber(n, k);
        String[] split = kDemicalNumber.split("0+");
        for(String demicalNumber : split){
            if(isPrimeNumber(demicalNumber,k)){
                answer++;
            }
        }
        return answer;
    }
    
    private String getDemicalNumber(int n, int k) {
        return Integer.toString(n, k);
    }

    private boolean isPrimeNumber(String number, int k) {
        long demicalNumber = Long.parseLong(number, 10);
        if (demicalNumber == 2 || demicalNumber == 3) {
            return true;
        } else if (demicalNumber == 1) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(demicalNumber); i++) {
            if (demicalNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}