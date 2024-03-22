import java.util.*;

class Solution {
    
    Map<String, Integer> indexTable = new HashMap<>();
    Map<String, int[]> giveToTable = new HashMap<>();
    Map<String, int[]> givenTable = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        initTable(friends);
        for (String gift : gifts){
            String[] people = gift.split(" ");
            exchangeGift(people[0], people[1]);
        }
        int[] giftRates =initGiftRate(friends);
        
        return getMaxValue(friends, giftRates);
    }
    public int getMaxValue(String[] friends, int[] giftRates){
        int answer = 0;
        
        for (int i=0; i< friends.length; i++){
            int temp = 0;
            int index = indexTable.get(friends[i]);
            int[] givingTable = giveToTable.get(friends[i]);   
            int[] givenTable1 = givenTable.get(friends[i]);
            for (int j=0; j< giveToTable.size(); j++){
                
                if (givingTable[j] > givenTable1[j]){
                    temp++;
                } else if (givingTable[j] == givenTable1[j] && giftRates[i] > giftRates[j]){
                    temp++;
                }
            }
            if(temp > answer){
                answer = temp;
            }
        }
        return answer;
    }
    
    
    
    public void initTable(String[] friends){
        int i = 0;
        for(String friend : friends){
            indexTable.put(friend,i);
            giveToTable.put(friend, new int[friends.length]);
            givenTable.put(friend, new int[friends.length]);
            i++;
        }
    }
    public int[] initGiftRate(String[] friends){
        int[] giftRates = new int[friends.length];
        for (int i=0; i<indexTable.size(); i++){
            int givingCount =0;
            int givenCount =0;
            int index = indexTable.get(friends[i]);
            int[] givingTable = giveToTable.get(friends[i]);   
            int[] givenTable1 = givenTable.get(friends[i]);
            for (int j=0; j< giveToTable.size(); j++){
                givingCount+=givingTable[j];
                givenCount+=givenTable1[j];
            }
            giftRates[i] = givingCount - givenCount;
        }
        return giftRates;
    }
    public void exchangeGift(String sender, String receiver){
        
        int senderIndex = indexTable.get(sender);
        int[] senderTable = giveToTable.get(sender);
        int receiverIndex = indexTable.get(receiver);
        int[] receiverTable = givenTable.get(receiver);
        
        senderTable[receiverIndex]++;
        receiverTable[senderIndex]++;
        giveToTable.put(sender,senderTable);
        givenTable.put(receiver,receiverTable);
    }
    
    
}