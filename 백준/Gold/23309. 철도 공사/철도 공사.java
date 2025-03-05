import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        int[] preArr = new int[1000001];  
        int[] postArr = new int[1000001]; 

        st = new StringTokenizer(br.readLine());
        int firstStation = Integer.parseInt(st.nextToken());
        int prevStation = firstStation;

        for (int i = 1; i < N - 1; i++) {
            int station = Integer.parseInt(st.nextToken());
            preArr[station] = prevStation;
            postArr[prevStation] = station;
            prevStation = station;
        }

        int lastStation = Integer.parseInt(st.nextToken());
        postArr[prevStation] = lastStation;
        preArr[lastStation] = prevStation;
        postArr[lastStation] = firstStation;
        preArr[firstStation] = lastStation;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int curStation = Integer.parseInt(st.nextToken());
            if (command.equals("BN")) {
                int newStation = Integer.parseInt(st.nextToken());
                int nextStation = postArr[curStation];
                sb.append(nextStation).append("\n");
                postArr[curStation] = newStation;
                preArr[newStation] = curStation;
                postArr[newStation] = nextStation;
                preArr[nextStation] = newStation;
            } else if (command.equals("BP")) {
                int newStation = Integer.parseInt(st.nextToken());
                int prevStationCommand = preArr[curStation];
                sb.append(prevStationCommand).append("\n");
                postArr[prevStationCommand] = newStation;
                preArr[newStation] = prevStationCommand;
                postArr[newStation] = curStation;
                preArr[curStation] = newStation;
            } else if (command.equals("CN")) {
                int nextStation = postArr[curStation];
                sb.append(nextStation).append("\n");
                int nextNextStation = postArr[nextStation];
                postArr[curStation] = nextNextStation;
                preArr[nextNextStation] = curStation;
            } else if (command.equals("CP")) {
                int prevStationCommand = preArr[curStation];
                sb.append(prevStationCommand).append("\n");
                int prevPrevStation = preArr[prevStationCommand];
                preArr[curStation] = prevPrevStation;
                postArr[prevPrevStation] = curStation;
            }
        }
        System.out.print(sb);
        br.close();
    }
}
