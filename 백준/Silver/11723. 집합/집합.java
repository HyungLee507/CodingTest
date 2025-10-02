import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());
        int set = 0; 
        final int ALL = (1 << 20) - 1;

        for (int i = 0; i < M; i++) {
            String cmd = br.readLine();
            char c0 = cmd.charAt(0);

            if (c0 == 'a') { 
                if (cmd.length() == 3) { 
                    set = ALL;
                } else { 
                    int sp = cmd.indexOf(' ');
                    int x = Integer.parseInt(cmd, sp + 1, cmd.length(), 10);
                    set |= (1 << (x - 1));
                }
            } else if (c0 == 'r') { 
                int sp = cmd.indexOf(' ');
                int x = Integer.parseInt(cmd, sp + 1, cmd.length(), 10);
                set &= ~(1 << (x - 1));
            } else if (c0 == 'c') { 
                int sp = cmd.indexOf(' ');
                int x = Integer.parseInt(cmd, sp + 1, cmd.length(), 10);
                bw.write(((set & (1 << (x - 1))) != 0) ? '1' : '0');
                bw.write('\n');
            } else if (c0 == 't') { 
                int sp = cmd.indexOf(' ');
                int x = Integer.parseInt(cmd, sp + 1, cmd.length(), 10);
                set ^= (1 << (x - 1));
            } else if (c0 == 'e') { 
                set = 0;
            }
        }

        bw.flush();
    }
}
