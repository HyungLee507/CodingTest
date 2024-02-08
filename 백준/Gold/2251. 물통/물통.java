import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static class Bottle {
        final int a;
        final int b;
        final int c;

        public Bottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }

    static Set<Integer> liters = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        int sizeC = Integer.parseInt(st.nextToken());
        bfs(sizeA, sizeB, sizeC);
        StringBuilder sb = new StringBuilder();
        liters.stream().sorted().forEach((value) -> sb.append(value).append(" "));
        System.out.println(sb);
    }

    private static void bfs(int sizeA, int sizeB, int sizeC) {

        Queue<Bottle> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[sizeA + 1][sizeB + 1];
        queue.add(new Bottle(0, 0, sizeC));

        while (!queue.isEmpty()) {
            Bottle poll = queue.poll();
            int a = poll.getA();
            int b = poll.getB();
            int c = poll.getC();
            if (!visited[a][b]) {
                visited[a][b] = true;
                if (a == 0) {
                    int value = sizeC - b;
                    liters.add(value);
                }
            } else {
                continue;
            }
            if (a + b >= sizeB) {
                queue.add(new Bottle(a - (sizeB - b), sizeB, c));
            } else {
                queue.add(new Bottle(0, a + b, c));
            }

            if (a + c >= sizeC) {
                queue.add(new Bottle(a - (sizeC - c), b, sizeC));
            } else {
                queue.add(new Bottle(0, b, a + c));
            }
            if (b + a >= sizeA) {
                queue.add(new Bottle(sizeA, b - (sizeA - a), c));
            } else {
                queue.add(new Bottle(b + a, 0, c));
            }
            if (b + c >= sizeC) {
                queue.add(new Bottle(a, b - (sizeC - c), sizeC));
            } else {
                queue.add(new Bottle(a, 0, b + c));
            }
            if (c + a >= sizeA) {
                queue.add(new Bottle(sizeA, b, c - (sizeA - a)));
            } else {
                queue.add(new Bottle(a + c, b, 0));
            }
            if (c + b >= sizeB) {
                queue.add(new Bottle(a, sizeB, c - (sizeB - b)));
            } else {
                queue.add(new Bottle(a, b + c, 0));
            }

        }


    }

}