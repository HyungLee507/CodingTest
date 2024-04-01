import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static final int APPLE = 1;
    static int[][] map;

    static LinkedList<BodyPart> bodyParts = new LinkedList<>();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        initMap();
        System.out.println(getGameTotalSecond());
    }

    private static int getGameTotalSecond() throws IOException {

        Map<Integer, String> information = getInformation();

        int second = 1;
        while (true) {
            BodyPart nextPart = bodyParts.peekFirst() != null ? bodyParts.peekFirst().getNextPart() : null;
            if (isEndGame(nextPart)) {
                return second;
            }
            bodyParts.addFirst(nextPart);
            if (isEatApple(nextPart)) {
                map[nextPart.x][nextPart.y] = 0;
            } else {
                bodyParts.removeLast();
            }
            if (information.containsKey(second)) {
                // 여기에서 방향 전환 로직
                String dir = information.get(second);
                changeDir(dir, bodyParts.peekFirst());
            }
            second++;
        }
    }

    private static void changeDir(String dir, BodyPart nextPart) {

        if (dir.equals("D")) {
            if (Objects.equals(nextPart.dir, "U")) {
                nextPart.setNewDir("R");
                return;
            }
            if (Objects.equals(nextPart.dir, "R")) {
                nextPart.setNewDir("D");
                return;
            }
            if (Objects.equals(nextPart.dir, "D")) {
                nextPart.setNewDir("L");
                return;
            }
            if (Objects.equals(nextPart.dir, "L")) {
                nextPart.setNewDir("U");
                return;
            }
        } else if (dir.equals("L")) {
            if (Objects.equals(nextPart.dir, "U")) {
                nextPart.setNewDir("L");
                return;
            }
            if (Objects.equals(nextPart.dir, "R")) {
                nextPart.setNewDir("U");
                return;
            }
            if (Objects.equals(nextPart.dir, "D")) {
                nextPart.setNewDir("R");
                return;
            }
            if (Objects.equals(nextPart.dir, "L")) {
                nextPart.setNewDir("D");
                return;
            }
        }
    }

    private static Map<Integer, String> getInformation() throws IOException {
        Map<Integer, String> information = new HashMap<>();
        int informationCount = parseInt(bf.readLine());
        for (int i = 0; i < informationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int second = parseInt(st.nextToken());
            String dir = st.nextToken();
            information.put(second, dir);
        }
        return information;
    }

    private static boolean isEatApple(BodyPart nextPart) {
        if (map[nextPart.x][nextPart.y] == APPLE) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isEndGame(BodyPart nextPart) {

        if (isEnterWall(nextPart) || isBody(nextPart)) {
            return true;
        }
        return false;
    }

    private static boolean isBody(BodyPart nextPart) {

        if (bodyParts.contains(nextPart)) {
            return true;
        }
        return false;
    }

    private static boolean isEnterWall(BodyPart nextPart) {
        if (nextPart.x >= map.length || nextPart.y >= map.length || nextPart.x < 1 || nextPart.y < 1) {
            return true;
        }
        return false;
    }


    private static void initMap() throws IOException {
        int size = parseInt(bf.readLine());
        map = new int[size + 1][size + 1];

        int iterationCount = parseInt(bf.readLine());
        for (int i = 0; i < iterationCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            map[x][y] = APPLE;
        }
        bodyParts.add(new BodyPart(1, 1, "R"));
    }

    private static class BodyPart {
        int x;
        int y;

        String dir;

        public BodyPart(int x, int y, String dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public BodyPart getNextPart() {
            if (dir == "U") {
                return new BodyPart(x - 1, y, "U");
            }
            if (dir == "D") {
                return new BodyPart(x + 1, y, "D");
            }
            if (dir == "R") {
                return new BodyPart(x, y + 1, "R");
            }
            if (dir == "L") {
                return new BodyPart(x, y - 1, "L");
            }
            return null;
        }

        public void setNewDir(String newDir) {
            this.dir = newDir;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            BodyPart bodyPart = (BodyPart) object;
            return x == bodyPart.x && y == bodyPart.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}