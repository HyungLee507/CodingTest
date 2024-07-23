import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int gate = Integer.parseInt(bf.readLine());
        // init
        parent = new int[gate + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int plane = Integer.parseInt(bf.readLine());
        int dockingCount = 0;
        boolean isBlocked = false;
        for (int i = 0; i < plane; i++) {
            int maxBound = Integer.parseInt(bf.readLine());
            // 여기에서 해당 bound 안에 값을 넣을 수 있고 아직 공항이 폐쇄가 되지 않았다면
            // 공항 폐쇄되면 그냥 지나가고
            // 공항 열려있고 안에 들어갈수 있는 공간이 있다면 넣고 아니면 공항 폐쇄
            if (!isBlocked) {
                if (canDocking(maxBound)) {
                    //여기서 union 해주면 됨.
                    union(maxBound);
                    dockingCount++;
                } else {
                    isBlocked = true;
                }
            }
        }
        System.out.println(dockingCount);
    }

    private static boolean canDocking(int maxBound) {
        if (find(maxBound) > 0) {
            return true;
        }
        return false;
    }

    private static void union(int num) {
        int value = find(num);
        if (value > 0) {
            parent[value]--;
        } else {
            return;
        }
    }

    private static int find(int num) {
        if (num == parent[num]) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }


}

