import java.util.*;

class Solution {
        long answer = 0;

        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            int deliveryIndex = deliveries.length - 1;
            int pickupIndex = pickups.length - 1;
            while (deliveryIndex >= 0 || pickupIndex >= 0) {
                deliveryIndex = getIndex(deliveries, deliveryIndex);
                pickupIndex = getIndex(pickups, pickupIndex);
                answer += (Integer.max(deliveryIndex, pickupIndex) + 1);
                deliveryIndex = process(deliveries, deliveryIndex, cap);
                pickupIndex = process(pickups, pickupIndex, cap);
            }
            return answer * 2;
        }

        public int process(int[] arr, int index, int cap) {
            if (index < 0) {
                return -1;
            }
            if (arr[index] > cap) {
                arr[index] -= cap;
                return index;
            } else {
            int temp = arr[index];
            arr[index] = 0;
            return process(arr, index-1, cap - temp);
            }
        }
    
        public int getIndex(int[] arr, int index){
            for (int i = index; i>=0 ; i--){
                if(arr[i] != 0){
                    return i;
                }
            }
            return -1;
        }
    
}