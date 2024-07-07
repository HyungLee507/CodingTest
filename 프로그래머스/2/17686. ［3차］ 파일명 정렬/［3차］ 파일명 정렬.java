import java.util.*;
class Solution {
    
    private static class File implements Comparable<File> {
        String name;
        String header;
        String number;
        String tail;
        int timeStamp;

        public File(String value, int timeStamp) {
            this.timeStamp = timeStamp;
            this.name = value;
            int splitIndex1 = 0;
            int splitIndex2 = value.length();
            for (int i = 0; i < value.length(); i++) {
                if (Character.isDigit(value.charAt(i))) {
                    splitIndex1 = i;
                    break;
                }
            }
            for (int i = splitIndex1; i < value.length(); i++) {
                if (!Character.isDigit(value.charAt(i))) {
                    splitIndex2 = i;
                    break;
                }
            }
            header = value.substring(0, splitIndex1);
            number = value.substring(splitIndex1, splitIndex2);
            tail = value.substring(splitIndex2, value.length());
            if (tail.isBlank()) {
                tail = "";
            }
        }

        @Override
        public int compareTo(File o) {
            if (this.header.toUpperCase().equals(o.header.toUpperCase())) {
                if (this.number.equals(o.number)) {
                    return Integer.compare(this.timeStamp, o.timeStamp);
                } else {
                    return Integer.compare(Integer.parseInt(this.number), Integer.parseInt(o.number));
                }
            } else {
                return this.header.toUpperCase().compareTo(o.header.toUpperCase());
            }
        }
    }
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < files.length; i++) {
            list.add(new File(files[i], time++));
        }
        Collections.sort(list);
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = list.get(i).name;
        }
        return result;
    }
}