import java.io.*;
import java.util.*;

public class Main {
    static class Member {
        int age;
        String name;
        int order; 

        public Member(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members.add(new Member(age, name, i));
        }

        members.sort(Comparator.comparingInt(m -> m.age));

        for (Member m : members) {
            sb.append(m.age).append(" ").append(m.name).append("\n");
        }

        System.out.print(sb);
    }
}
