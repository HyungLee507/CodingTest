import java.util.*;
class Solution {
    
    private static class Record{
        String name;
        Set<String> reporters;
        Set<String> complainted;
        public Record(String name){
            name = this.name;
            reporters = new HashSet<>();
        }
    
        private void addReporter(String name){
            reporters.add(name);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Record record = (Record) o;
            return Objects.equals(name, record.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
        
        
        
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> idMap = new HashMap<>();
        List<Record> records = new ArrayList<>();
        for(int i=0; i<id_list.length; i++){
            idMap.put(id_list[i] ,i);
            records.add(new Record(id_list[i]));
        }
        for(String content : report){
            String[] split = content.split(" ");
            String reporter = split[0];
            String reported = split[1];
            int reportedIndex = idMap.get(reported);
            records.get(reportedIndex).addReporter(reporter);
        }
        
        for(int i=0; i<id_list.length; i++){
            Record record = records.get(i);
            if(record.reporters.size() >= k){
                for(String reporter : record.reporters){
                    answer[idMap.get(reporter)]++;
                }
            }
        }
        return answer;
    }
}