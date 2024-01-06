import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final String MALE = "1";
    public static final String FEMALE = "2";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int switchCount = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] switches = new int[switchCount];
        for (int i = 0; i < switchCount; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        int peopleCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < peopleCount; i++) {
            String[] splitInstruction = bf.readLine().split(" ");
            int switchNumber = Integer.parseInt(splitInstruction[1]);
            if (splitInstruction[0].equals(MALE)) {
                maleChange(switches, switchNumber);
            }
            if (splitInstruction[0].equals(FEMALE)) {
                femaleChange(switches, switchNumber);
            }
        }
        for (int i = 0; i < switches.length; i++) {
            if (i % 20 == 0 && i != 0) {
                System.out.println();
            }
            System.out.printf("%d ", switches[i]);
        }
    }

    private static void maleChange(int[] switches, int switchNumber) {
        for (int j = 1; j <= switches.length / switchNumber; j++) {
            if (switches[j * switchNumber - 1] == 1) {
                switches[j * switchNumber - 1] = 0;
            } else if (switches[j * switchNumber - 1] == 0) {
                switches[j * switchNumber - 1] = 1;
            }
        }
    }

    private static void femaleChange(int[] switches, int switchNumber) {
        int extendedSize = 0;
        while (true) {
//            if (isOutOfBound(switches, switchNumber, extendedSize)
//                    || isSameBoolean(switches, switchNumber, extendedSize)) {
            if (isSameBoolean(switches, switchNumber, extendedSize)) {
                break;
            }
            extendedSize++;
        }
        if (switches[switchNumber - 1] == 1) {
            switches[switchNumber - 1] = 0;
        } else if (switches[switchNumber - 1] == 0) {
            switches[switchNumber - 1] = 1;
        }

        for (int i = 1; i <= extendedSize; i++) {
            if (switches[switchNumber - 1 - i] == 1) {
                switches[switchNumber - 1 - i] = 0;
                switches[switchNumber - 1 + i] = 0;
            } else if (switches[switchNumber - 1 - i] == 0) {
                switches[switchNumber - 1 - i] = 1;
                switches[switchNumber - 1 + i] = 1;
            }
        }
    }

    private static boolean isSameBoolean(int[] switches, int switchNumber, int extendedSize) {

        try {
            return switches[switchNumber - 1 + extendedSize + 1] != switches[switchNumber - 1 - extendedSize - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }

    }

}