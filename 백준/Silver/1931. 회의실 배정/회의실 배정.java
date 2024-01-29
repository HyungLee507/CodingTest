import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Meeting implements Comparable<Meeting> {

        final int startTime;
        final int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.endTime == o.endTime) {
                return this.startTime - o.startTime;
            }
            return this.endTime - o.endTime;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < meetingCount; i++) {
            st = new StringTokenizer(bf.readLine());
            Meeting meeting = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            meetings.add(meeting);
        }
        Collections.sort(meetings);

        int startTime = 0;
        int maxMeetingCount = 0;

        for (Meeting meeting : meetings) {
            if (meeting.getStartTime() >= startTime) {
                startTime = meeting.getEndTime();
                maxMeetingCount++;
            }
        }

        System.out.println(maxMeetingCount);

    }

}