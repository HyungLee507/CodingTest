import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static int[] team;
    static int[] enemy;

    // 원수의 원수도 친구고 -> 원수를 따로 설정해야 되나...
    // 내 친구의 친구도 친구다 -> 이건 일단 union-find
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int studentCount = Integer.parseInt(bf.readLine());
        team = new int[studentCount + 1];
        enemy = new int[studentCount + 1];
        for (int i = 1; i <= studentCount; i++) {
            team[i] = i;
        }

        int iterationCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < iterationCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());
            if (command.equals("F")) {
                unionFriend(person1, person2);
            } else {
                unionEnemy(person1, person2);
            }
        }
        Set<Integer> group = new HashSet<>();
        for (int i = 1; i < team.length; i++) {
            group.add(find(i));
        }
        System.out.println(group.size());
    }

    private static void unionEnemy(int person1, int person2) {
        // 여기서 이제 서로를 적으로 연결하고
        int opTeam1 = find(person2);
        // 적이 공통인 애들 unionFriend로 묶어버리면 됨.
        if (enemy[person1] == 0) {
            enemy[person1] = opTeam1;
        } else {
            unionFriend(opTeam1, find(enemy[person1]));
        }
        int opTeam2 = find(person1);
        if (enemy[person2] == 0) {
            enemy[person2] = opTeam2;
        } else {
            unionFriend(opTeam2, find(enemy[person2]));
        }


    }


    private static void unionFriend(int person1, int person2) {
        int team1 = find(person1);
        int team2 = find(person2);
        if (team1 != team2) {
            team[team2] = team1;
        }
    }


    private static int find(int x) {
        if (x == team[x]) {
            return x;
        }
        return team[x] = find(team[x]);
    }

    private static int findEnemy(int x) {
        if (x == enemy[x]) {
            return x;
        }
        return enemy[x] = findEnemy(enemy[x]);
    }


}

