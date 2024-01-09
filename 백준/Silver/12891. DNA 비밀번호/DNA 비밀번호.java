import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] passwordRule = new int[4];
    static int[] window = new int[4];
    static int passwords = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int dnaSize = Integer.parseInt(st.nextToken());
        int passwordSize = Integer.parseInt(st.nextToken());
        String dna = bf.readLine();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            passwordRule[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < dnaSize - passwordSize + 1; i++) {
            if (i != 0) {
                if (dna.charAt(i - 1) == 'A') {
                    window[0]--;
                } else if (dna.charAt(i - 1) == 'C') {
                    window[1]--;
                } else if (dna.charAt(i - 1) == 'G') {
                    window[2]--;
                } else if (dna.charAt(i - 1) == 'T') {
                    window[3]--;
                }
                if (dna.charAt(i + passwordSize - 1) == 'A') {
                    window[0]++;
                } else if (dna.charAt(i + passwordSize - 1) == 'C') {
                    window[1]++;
                } else if (dna.charAt(i + passwordSize - 1) == 'G') {
                    window[2]++;
                } else if (dna.charAt(i + passwordSize - 1) == 'T') {
                    window[3]++;
                }
                isCanPassword();
            } else {
                appliedRule(dna.substring(i, i + passwordSize));
            }
        }
        System.out.println(passwords);
    }

    private static void appliedRule(String subDna) {
        for (char dnaOne : subDna.toCharArray()) {
            if (dnaOne == 'A') {
                window[0]++;
            }
            if (dnaOne == 'C') {
                window[1]++;
            }
            if (dnaOne == 'G') {
                window[2]++;
            }
            if (dnaOne == 'T') {
                window[3]++;
            }
        }
        isCanPassword();
    }

    private static void isCanPassword() {
        for (int i = 0; i < 4; i++) {
            if (window[i] < passwordRule[i]) {
                return;
            }
        }
        passwords++;
    }


}