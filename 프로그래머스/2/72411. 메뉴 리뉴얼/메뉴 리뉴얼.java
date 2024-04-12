import java.util.*;
import java.util.Map.Entry;
class Solution {
    
    boolean[] visited ;
    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            char[] characters = orders[i].toCharArray();
            Arrays.sort(characters);
            orders[i] = new String(characters);
        }
        List<String> courseList = new ArrayList<>();
        for (int orderCount : course) {
            courseList.addAll(getCourseDish(orders, orderCount));
        }
        Collections.sort(courseList);
        String[] array = courseList.toArray(new String[0]);
        return array;
    }
    

    private List<String> getCourseDish(String[] orders, int orderCount) {
        List<String> courseDishByCount = new ArrayList<>();
        Map<String, Integer> data = new HashMap<>();
        for (String order : orders) {
            visited = new boolean[order.length()];
            addCourseData(data, order, "", orderCount, 0);
        }
        int maxValue = data.entrySet().stream().filter(e -> e.getKey().length() == orderCount && e.getValue() >= 2)
                .mapToInt(Map.Entry::getValue)
                .max()
                .orElse(-1);
        for (Entry<String, Integer> stringIntegerEntry : data.entrySet()) {
            if (stringIntegerEntry.getValue() == maxValue) {
                courseDishByCount.add(stringIntegerEntry.getKey());
            }
        }
        return courseDishByCount;
    }
    private void addCourseData(Map<String, Integer> data, String order, String temp, int orderCount, int index) {
        if (temp.length() == orderCount) {
            if (data.containsKey(temp)) {
                int i = data.get(temp);
                data.put(temp, i + 1);
            } else {
                data.put(temp, 1);
            }
            return;
        }
        if (index < order.length()) {
            visited[index] = true;
            StringBuilder sb = new StringBuilder(temp);
            addCourseData(data, order, sb.append(order.charAt(index)).toString(), orderCount, index + 1);
            visited[index] = false;
            addCourseData(data, order, temp, orderCount, index + 1);
        }

    }
}