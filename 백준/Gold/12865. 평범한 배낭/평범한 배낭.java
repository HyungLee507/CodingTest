import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static class KnapSack {
		int weight;
		int value;

		public KnapSack(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<KnapSack> knapSacks = new ArrayList<>();
		int[] cost = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			knapSacks.add(new KnapSack(weight, value));
		}
		int[][] dp = new int[N + 1][K + 1];

		for (int k = 1; k <= K; k++) { // 무게
			for (int i = 1; i <= N; i++) { // item
				dp[i][k] = dp[i - 1][k];
				if (k - knapSacks.get(i - 1).weight >= 0) {
					dp[i][k] = Math.max(dp[i - 1][k],
							knapSacks.get(i - 1).value + dp[i - 1][k - knapSacks.get(i - 1).weight]);
				}
			}
		}
		System.out.println(dp[N][K]);

	}

}
