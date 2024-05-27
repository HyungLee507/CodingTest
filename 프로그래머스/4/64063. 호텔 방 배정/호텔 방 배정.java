import java.util.*;

class Solution {
    Map<Long, Long> map;
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        map = new HashMap<>();
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = getNextRoom(room_number[i]);
        }
        return answer;
    }
    
    private long getNextRoom(long roomNumber) {
        if (!map.containsKey(roomNumber)) {
            map.put(roomNumber, roomNumber + 1);
            return roomNumber;
        }
        long next = map.get(roomNumber);
        long empty = getNextRoom(next);
        map.put(roomNumber, empty);
        return empty;
    }
}