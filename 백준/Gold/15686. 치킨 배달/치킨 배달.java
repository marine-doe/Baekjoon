import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<int[]> chicken;
	static int[][] visited;
	static int[][] map;
	static int n, m, sum, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n][n];
		chicken = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}

		visited = new int[chicken.size()][2];
		
		result = Integer.MAX_VALUE;
		dfs(0, 0);
		System.out.println(result);
	}

	private static void dfs(int cnt, int depth) {
		if (cnt == m) {
			sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] == 1) {
						measureDistance(i, j);
					}
				}
			}
			result = Math.min(sum, result);
			return;
		}

		if (depth == chicken.size()) {
			return;
		}

		visited[cnt][0] = chicken.get(depth)[0];
		visited[cnt][1] = chicken.get(depth)[1];
		dfs(cnt + 1, depth + 1);
		dfs(cnt, depth + 1);
	}

	private static void measureDistance(int x, int y) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			int dist = Math.abs(visited[i][0] - x) + Math.abs(visited[i][1] - y);
			min = Math.min(min, dist);
		}
		sum += min;
	}
}