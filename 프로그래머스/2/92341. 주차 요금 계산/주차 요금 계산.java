import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        List<Integer> carList = getCarList(records);
        List<Record> carRecords = new ArrayList<>();
        for (int car : carList){
            carRecords.add(new Record(car));
        }
        Collections.sort(carRecords);
        for(String record : records){
            String[] data = record.split(" ");
            int carNumber = Integer.parseInt(data[1]);
            for(Record carRecord : carRecords){
                if (carNumber==carRecord.carNumber){
                    carRecord.minutes.add(data[0]);
                }
            }
        }
        int[] answer = new int[carRecords.size()];
        for (Record carRecord : carRecords) {
            carRecord.getTotalMinute();
        }
        for (int i=0; i<carRecords.size(); i++){
            int price =fees[1];
            int temp = carRecords.get(i).totalMin - fees[0];
            if (temp > 0) {
                if (temp % fees[2] == 0) {
                    price += (temp / fees[2] * fees[3]);
                } else {
                    price += ((temp / fees[2] + 1) * fees[3]);
                }
            }
            answer[i] = price;
        }
        return answer;
    }
    private List<Integer> getCarList(String[] records){
        List<Integer> carList = new ArrayList<>();
        for (String record : records){
            String[] data = record.split(" ");
            if(!carList.contains(Integer.parseInt(data[1]))){
                carList.add(Integer.parseInt(data[1]));
            }
        }
        return carList;
    }
    
    
    private static class Record implements Comparable<Record>{
        int carNumber;
        List<String> minutes = new ArrayList<>();
        int totalMin;
    
        Record(int carNumber){
            this.carNumber = carNumber;
            this.totalMin = 0;
        }
        public void addMintue(String mintue){
            minutes.add(mintue);
        }
        public int getTotalMinute(){
            for (int i = 1; i < minutes.size(); i += 2) {
                String out = minutes.get(i);
                String in = minutes.get(i - 1);
                totalMin += getDiffMin(out, in);
            }
            if(minutes.size()%2!=0){
                String out = "23:59";
                String in = minutes.get(minutes.size()-1);
                totalMin += getDiffMin(out ,in);
            }
            return totalMin;
        }
        private int getDiffMin(String out, String in){
            int totalTime = 0;
            String[] outTime = out.split(":");
            String[] inTime = in.split(":");
            totalTime += ((Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0]))*60);
            if((Integer.parseInt(outTime[1]) > (Integer.parseInt(inTime[1])))){
               totalTime +=  ((Integer.parseInt(outTime[1]) - (Integer.parseInt(inTime[1]))));
            }else{
                totalTime -=  ((Integer.parseInt(inTime[1]) - (Integer.parseInt(outTime[1]))));
            }
            return totalTime;
        }
        
        @Override
        public int compareTo(Record o) {
            return carNumber - o.carNumber;
        }    
    }
}