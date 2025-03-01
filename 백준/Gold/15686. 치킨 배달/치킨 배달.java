
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static List<int[]> houses = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();
		dfs(0, new LinkedList<int[]>());
		System.out.println(answer);
	}

	private static void init() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					houses.add(new int[] { i, j });
				}
			}
		}
		bf.close();
	}

	private static void dfs(int num, LinkedList<int[]> candidate) {
		// m의 개수랑 같으면 개수 세고 return 으로 종료
		if (candidate.size() == m) {
			getMinimum(candidate);
			return;
		}
		if (num >= (n * n)) {
			return;
		}
		// 기본적으로 포함하지 않고 돌리
		// 치킨집이면 포함하고 돌리고
		if (map[(num / n) + 1][(num % n) + 1] == 2) {
			candidate.add(new int[] { (num / n) + 1, (num % n) + 1 });
			dfs(num + 1, candidate);
			candidate.remove(candidate.size() - 1);
		}
		dfs(num + 1, candidate);
	}

	private static void getMinimum(LinkedList<int[]> candidate) {
		int totalCost = 0;
		for (int i = 0; i < houses.size(); i++) {
			int[] temp = houses.get(i);
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < candidate.size(); j++) {
				int[] temp2 = candidate.get(j);
				min = Math.min(min, Math.abs(temp[0] - temp2[0]) + Math.abs(temp[1] - temp2[1]));
			}
			totalCost += min;
		}
		answer = Math.min(totalCost, answer);
	}

}
