import java.io.*;
import java.util.*;

public class Main {

	static int N, M, maxTime;
	static int[] attractions;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		attractions = new int[M];

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M; i++){
			int m = Integer.parseInt(st.nextToken());
			attractions[i] = m;
			maxTime = Math.max(maxTime, m);
		}

		if(N <= M) {
			System.out.println(N);
		}

		if(N > M) {
			long resultTime = solve();
			long lastCount = findCount(resultTime - 1);

			for(int i = 0 ; i < M; i++) {
				if(resultTime % attractions[i] == 0) {
					lastCount++;
				}
				if(lastCount == N) {
					System.out.println(i + 1);
					break;
				}
			}
		}

	}

	static long solve() {
		long left = 0;
		long right = 30 * 2_000_000_000L +1;
		long result = 0;

		while(left + 1 < right) {
			long mid = (left + right) / 2;
			if(findCount(mid) < N) {
				left = mid;
			} else {
				right = mid;
			}
		}

		return right;
	}

	static long findCount(long time) {
		long count = M;

		for(int i = 0; i < M; i++) {
			count += time / attractions[i];
		}

		return count;
	}
}
