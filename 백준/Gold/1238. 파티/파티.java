import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Road implements Comparable<Road> {
        int start;
        int end;
        int time;

        public Road(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return time - o.time;
        }
    }

    static List<Road>[] roads;
    static int destinationTown, studentCount;
    static int[] times;
    static int[] totalTimes;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        initData();
        findAtStartTown();
        findAllTown();
        int max = Arrays.stream(totalTimes).max().orElse(-1);
        System.out.println(max);
    }

    private static void findAllTown() {
        times = new int[studentCount + 1];
        visited = new boolean[studentCount + 1];
        for (int j = 1; j <= studentCount; j++) {
            if (j == destinationTown) {
                continue;
            }
            times[j] = Integer.MAX_VALUE;
        }
        searchDestination(destinationTown);
        for (int i = 1; i <= studentCount; i++) {
            totalTimes[i] += times[i];
        }

    }

    private static void findAtStartTown() {
        for (int i = 1; i <= studentCount; i++) {
            if (initSearchData(i)) {
                continue;
            }
            searchDestination(i);
            totalTimes[i] += times[destinationTown];
        }
    }

    private static boolean initSearchData(int i) {
        if (i == destinationTown) {
            return true;
        }
        times = new int[studentCount + 1];
        visited = new boolean[studentCount + 1];
        for (int j = 1; j <= studentCount; j++) {
            if (j == i) {
                continue;
            }
            times[j] = Integer.MAX_VALUE;
        }
        return false;
    }

    private static void searchDestination(int startTown) {

        PriorityQueue<Road> queue = new PriorityQueue<>();
        visited[startTown] = true;
        for (Road road : roads[startTown]) {
            queue.add(new Road(road.start, road.end, road.time));
        }
        while (!queue.isEmpty()) {
            Road now = queue.poll();
            int endTown = now.end;
            if (!visited[endTown] && times[endTown] > now.time) {
                visited[endTown] = true;
                times[endTown] = now.time;
            }
            for (Road road : roads[endTown]) {
                if (!visited[road.end]) {
                    queue.add(new Road(endTown, road.end, road.time + now.time));
                }
            }
        }


    }

    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        studentCount = Integer.parseInt(st.nextToken());
        int roadCount = Integer.parseInt(st.nextToken());
        destinationTown = Integer.parseInt(st.nextToken());
        totalTimes = new int[studentCount + 1];
        roads = new List[studentCount + 1];
        for (int i = 1; i <= studentCount; i++) {
            roads[i] = new ArrayList<>();
        }
        for (int i = 0; i < roadCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int startTown = Integer.parseInt(st.nextToken());
            int endTown = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            roads[startTown].add(new Road(startTown, endTown, time));
        }
    }


}
