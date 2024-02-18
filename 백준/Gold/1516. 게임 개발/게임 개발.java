import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] degree;
    static List<Integer>[] rules;

    static List<Building> buildings = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int buildingCount = Integer.parseInt(st.nextToken());

        degree = new int[buildingCount + 1];
        rules = new List[buildingCount + 1];

        for (int i = 0; i <= buildingCount; i++) {
            rules[i] = new ArrayList<>();
        }

        for (int i = 1; i <= buildingCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int buildingTime = Integer.parseInt(st.nextToken());
            Building building = new Building(buildingTime, i);
            buildings.add(building);
            while (true) {
                int value = Integer.parseInt(st.nextToken());
                if (value == -1) {
                    break;
                }
                degree[i]++;
                rules[value].add(i);
            }
        }

        Queue<Building> queue = new ArrayDeque<>();
        for (int i = 0; i < buildingCount; i++) {
            if (degree[i + 1] == 0) {
                queue.add(buildings.get(i));
            }
        }
        while (!queue.isEmpty()) {
            Building poll = queue.poll();
            int index = poll.getIndex();
            for (Integer i : rules[index]) {
                degree[i]--;
                if (degree[i] == 0) {
                    buildings.get(i - 1).buildingTotalTime(poll.getRequiredBuildTotalTime());
                    queue.add(buildings.get(i - 1));
                } else {
                    buildings.get(i - 1).buildingTotalTime(poll.getRequiredBuildTotalTime());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        buildings.forEach((building -> sb.append(building.getRequiredBuildTotalTime()).append("\n")));
        System.out.println(sb);

    }

    static class Building {
        private final int buildingTime;

        private final int index;

        private int requiredBuildTotalTime;

        public Building(int buildingTime, int index) {
            this.buildingTime = buildingTime;
            this.requiredBuildTotalTime = buildingTime;
            this.index = index;
        }

        public void calcBuildingTotalTime(int addedBuildingTime) {
            requiredBuildTotalTime += addedBuildingTime;
        }

        public void buildingTotalTime(int addedBuildingTime) {
            if (addedBuildingTime + buildingTime > requiredBuildTotalTime) {
                requiredBuildTotalTime = buildingTime + addedBuildingTime;
            }

        }

        public int getIndex() {
            return index;
        }

        public int getRequiredBuildTotalTime() {
            return requiredBuildTotalTime;
        }

        public int getBuildingTime() {
            return buildingTime;
        }
    }


}