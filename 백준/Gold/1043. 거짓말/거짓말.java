import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static Set<Integer> witnesses = new HashSet<>();
    static int[] knownPeoples;
    static boolean[] leaderInParty;

    static List<List<Integer>> parties = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int people = Integer.parseInt(st.nextToken());
        knownPeoples = IntStream.range(0, people + 1).toArray();
        int partyNumber = Integer.parseInt(st.nextToken());
        leaderInParty = new boolean[partyNumber];
        st = new StringTokenizer(bf.readLine());
        int totalWitness = Integer.parseInt(st.nextToken());

        for (int i = 0; i < totalWitness; i++) {
            witnesses.add(Integer.parseInt(st.nextToken()));
        }
        int canLieParties = partyNumber;

        for (int i = 0; i < partyNumber; i++) {
            st = new StringTokenizer(bf.readLine());
            int peoplesInParty = Integer.parseInt(st.nextToken());
            List<Integer> members = new ArrayList<>();
            int firstNumber = Integer.parseInt(st.nextToken());
            members.add(firstNumber);
            for (int j = 1; j < peoplesInParty; j++) {
                int member = Integer.parseInt(st.nextToken());
                union(firstNumber, member);
                members.add(member);
            }
            parties.add(members);
        }

        for (List<Integer> party : parties) {
            for (Integer number : party) {
                if (witnesses.contains(find(number))) {
                    canLieParties--;
                    break;
                }
            }
        }

        System.out.println(canLieParties);


    }

    private static int find(int member) {

        if (knownPeoples[member] == member) {
            return member;
        }
        return knownPeoples[member] = find(knownPeoples[member]);
    }

    private static void union(int num1, int num2) {
        int value1 = find(num1);
        int value2 = find(num2);

        if (witnesses.contains(value2)) {
            knownPeoples[value1] = value2;
            return;
        }
        knownPeoples[value2] = value1;
    }
}