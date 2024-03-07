import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static class Consulting {
        int startDay;
        int endDay;
        int cost;

        public Consulting(int startDay, int endDay, int cost) {
            this.startDay = startDay;
            this.endDay = endDay;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int days = Integer.parseInt(bf.readLine());
        int[] totalCost = new int[days + 1];
        LinkedList<Consulting>[] consultings = new LinkedList[days + 1];
        for (int i = 1; i <= days; i++) {
            consultings[i] = new LinkedList<>();
        }
        for (int i = 1; i <= days; i++) {
            st = new StringTokenizer(bf.readLine());
            int continueDay = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (i + continueDay - 1 <= days) {
                consultings[i + continueDay - 1].add(new Consulting(i, i + continueDay - 1, cost));
            }
        }
        for (int i = 1; i <= days; i++) {
            totalCost[i] = totalCost[i - 1];
            int temp = totalCost[i];
            for (int j = 0; j < consultings[i].size(); j++) {
                temp = Math.max(temp,
                        consultings[i].get(j).cost + totalCost[consultings[i].get(j).startDay - 1]);
            }
            totalCost[i] = temp;
        }
        System.out.println(totalCost[days]);

    }
}