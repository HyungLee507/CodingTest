import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] adjuctCities;

    static int[] citizen;
    static boolean[] visited;
    static boolean[] checked;
    static int citizenDiff = Integer.MAX_VALUE;

    static int totalCitizen = 0;


    public static void main(String[] args) throws IOException {
        initData();
        int sectionCount = getSectionCount();
        if (sectionCount > 2) {
            System.out.println(-1);
            return;
        }
        visited = new boolean[citizen.length];
        divide(0, 0);
        System.out.println(citizenDiff);
    }

    private static int getSectionCount() {
        int sectionCount = 0;
        for (int i = 1; i <= citizen.length; i++) {
            if (!visited[i]) {
                dfs1(i);
                sectionCount++;
            }
            if (isCheckAllSection()) {
                break;
            }
        }
        return sectionCount;
    }

    private static void dfs1(int i) {
        visited[i] = true;
        List<Integer> adjustList = adjuctCities[i];
        for (Integer city : adjustList) {
            if (!visited[city]) {
                dfs1(city);
            }
        }
    }


    private static boolean isCheckAllSection() {
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }


    private static void divide(int count, int citizenCount) {
        if (count == citizen.length - 1) {
            List<Integer> section1 = new ArrayList<>();
            List<Integer> section2 = new ArrayList<>();
            for (int i = 1; i < visited.length; i++) {
                if (!visited[i]) {
                    section1.add(i);
                } else {
                    section2.add(i);
                }
            }
            if (section1.isEmpty() || section2.isEmpty()) {
                return;
            }
            //todo : 여기서 나눠진 그룹을 토대로 그들끼리 연결되는지 확인해야겠네
            if (isAllConnected(section1) && isAllConnected(section2)) {
                citizenDiff = Math.min(citizenDiff, Math.abs((totalCitizen - 2 * citizenCount)));
            }
            return;

        }
        visited[count + 1] = true;
        divide(count + 1, citizenCount + citizen[count + 1]);
        visited[count + 1] = false;
        divide(count + 1, citizenCount);

    }

    private static boolean isAllConnected(List<Integer> section) {
        Queue<Integer> queue = new LinkedList<>();
        checked = new boolean[citizen.length];
        checked[section.get(0)] = true;
        queue.offer(section.get(0));

        int count = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < adjuctCities[cur].size(); i++) {
                int y = adjuctCities[cur].get(i);
                if (section.contains(y) && !checked[y]) {
                    queue.offer(y);
                    checked[y] = true;
                    count++;
                }
            }
        }
        if (count == section.size()) {
            return true;
        } else {
            return false;
        }
    }


    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(bf.readLine());
        adjuctCities = new ArrayList[nodeCount + 1];
        citizen = new int[nodeCount + 1];
        visited = new boolean[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            adjuctCities[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= nodeCount; i++) {
            citizen[i] = Integer.parseInt(st.nextToken());
            totalCitizen += citizen[i];
        }
        for (int i = 1; i <= nodeCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int temp = Integer.parseInt(st.nextToken());
                adjuctCities[i].add(temp);
            }
        }
    }


}