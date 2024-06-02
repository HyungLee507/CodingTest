import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int N = 0;
    public static final int S = 1;

    static List<Gear> gears = new ArrayList<>();
    static boolean[] visited;

    private static class Gear {

        private static final int LEFT = 1;
        private static final int RIGHT = -1;

        List<Integer> sawTeeth;


        private void rotation(int rotationDir) {
            if (rotationDir == 1) {
                int remove = sawTeeth.remove(sawTeeth.size() - 1);
                sawTeeth.add(0, remove);
            }
            if (rotationDir == -1) {
                int remove = sawTeeth.remove(0);
                sawTeeth.add(remove);
            }
        }

        private boolean haveToTurnLEFT(Gear left) {
            if (!left.sawTeeth.get(2).equals(this.sawTeeth.get(6))) {
                return true;
            }

            return false;
        }

        private boolean haveToTurnRIGHT(Gear right) {
            if (!right.sawTeeth.get(6).equals(this.sawTeeth.get(2))) {
                return true;
            }
            return false;
        }


        public Gear(List<Integer> sawTeeth) {
            this.sawTeeth = sawTeeth;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        initGear(bf);
        int iterationCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < iterationCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int gearIndex = Integer.parseInt(st.nextToken());
            int rotationDir = Integer.parseInt(st.nextToken());
            List<int[]> rotationSeq = new ArrayList<>();
            visited = new boolean[5];
            rotationSeq.add(new int[]{gearIndex, rotationDir});
            getRotations(rotationSeq, gearIndex, rotationDir);
            for (int[] values : rotationSeq) {
                int index = values[0];
                int dir = values[1];
                gears.get(index - 1).rotation(dir);
            }
        }
        getScore();
    }

    private static void getRotations(List<int[]> rotationSeq, int gearIndex, int rotationDir) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{gearIndex, rotationDir});
        visited[gearIndex] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int index = poll[0]; //1~4
            int dir = poll[1];
            Gear gear = gears.get(index - 1);
            if (index + 1 <= gears.size() && index + 1 >= 1 && !visited[index + 1] && gear.haveToTurnRIGHT(
                    gears.get(index))) {
                visited[index + 1] = true;
                rotationSeq.add(new int[]{index + 1, dir * -1});
                queue.add(new int[]{index + 1, dir * -1});
            }
            if (index - 1 <= gears.size() && index - 1 >= 1 && !visited[index - 1] && gear.haveToTurnLEFT(
                    gears.get(index - 2))) {
                visited[index - 1] = true;
                rotationSeq.add(new int[]{index - 1, dir * -1});
                queue.add(new int[]{index - 1, dir * -1});
            }
        }

    }


    private static void getScore() {
        int totalScore = 0;
        int value = 1;
        int multiply = 2;
        for (Gear gear : gears) {
            if (gear.sawTeeth.get(0) == S) {
                totalScore += value;
            }
            value *= multiply;
        }
        System.out.println(totalScore);
    }

    private static void initGear(BufferedReader bf) throws IOException {
        for (int i = 0; i < 4; i++) {
            String sawTooth = bf.readLine();
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                if (sawTooth.charAt(j) == '1') {
                    temp.add(S);
                } else {
                    temp.add(N);
                }
            }
            gears.add(new Gear(temp));
        }
    }
}