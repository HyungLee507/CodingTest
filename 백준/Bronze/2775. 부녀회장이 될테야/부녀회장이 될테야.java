import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < testCount; i++) {
            int floor = Integer.parseInt(bf.readLine());
            int houseNumber = Integer.parseInt(bf.readLine());
            //Todo : 여기에는 출력값 함수
            System.out.println(residentNumber(floor, houseNumber));
        }
    }

    public static int residentNumber(int floor, int houseNumber) {

        if (floor == 0) {
//            int downstairsResidents = 0;
//            for (int i = 1; i <= houseNumber; i++) {
//                downstairsResidents += i;
//            }
//            System.out.println(floor + " 0층의 사람들은 " + downstairsResidents + "명 입니다.");
            return houseNumber;
        } else {
            int totalResidents = 0;
            for (int i = 1; i <= houseNumber; i++) {
                totalResidents += residentNumber(floor - 1, i);
            }
//            System.out.println(floor + "층의 사람들은 " + totalResidents + "명 입니다.");

            return totalResidents;
        }
    }
}
