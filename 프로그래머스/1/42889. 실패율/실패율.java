import java.util.*;
class Solution {
    private static class FailRate implements Comparable<FailRate>{
        int stage;
        int challenger;
        int failCount;
        float rate;
        public FailRate(int stage,int challenger, int failCount){
            this.stage=stage;
            this.challenger = challenger;
            this.failCount = failCount;
            if (challenger == 0) {
                rate = 0;
            } else {
                rate = (float) failCount / challenger;
            }
        }
        @Override
        public int compareTo(FailRate o){
            if(this.rate == o.rate){
                return Integer.compare(this.stage,o.stage);
            }else{
                return Float.compare(o.rate, this.rate);
            }
        }
    }
    public int[] solution(int N, int[] stages) {
        int challengers = stages.length;
        List<FailRate> rates = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stages.length; i++) {
            int count = map.getOrDefault(stages[i], 0);
            map.put(stages[i], count + 1);
        }
        for (int i = 1; i <= N; i++) {
            int failMembers = map.getOrDefault(i, 0);
            rates.add(new FailRate(i, challengers, failMembers));
            challengers -= failMembers;
        }
        Collections.sort(rates);
        int[] answer = new int[rates.size()];
        for (int i = 0; i < rates.size(); i++) {
            answer[i] = rates.get(i).stage;
        }
        return answer;
    }
}