import java.util.Scanner;

public class Main {
	static boolean[][] visited;
	static int[][] map;
	static int[] paper;
	static int n, m, total, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = 10;
		m = 5;
		visited = new boolean[n][n];
		map = new int[n][n];
		paper = new int[m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		result = Integer.MAX_VALUE;
		total = 0;
		dfs(0, 0);

		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}

	private static void dfs(int x, int y) {
		if(result <= total) {
			return;
		}
		
		if (x == 10) {
			int sum = 0;
			for (int i = 0; i < 5; i++) {
				sum += paper[i];
			}
			result = Math.min(result, sum);
			return;
		} else if (y == 10) {
			dfs(x + 1, 0);
			return;
		}

		if (map[x][y] == 1 && !visited[x][y]) {
			outer: for (int d = 4; d >= 0; d--) {
				if (x + d < n && y + d < n) {
					for (int i = x; i <= x + d; i++) {
						for (int j = y; j <= y + d; j++) {
							if (map[i][j] == 0 || visited[i][j]) {
								continue outer;
							}
						}
					}
					if (paper[d] < 5) {
						paper[d]++;
						total++;
						for (int i = x; i <= x + d; i++) {
							for (int j = y; j <= y + d; j++) {
								visited[i][j] = true;
							}
						}

						dfs(x, y + d + 1);

						paper[d]--;
						total--;
						for (int i = x; i <= x + d; i++) {
							for (int j = y; j <= y + d; j++) {
								visited[i][j] = false;
							}
						}
					} else if (d == 0 && paper[d] == 5) {
						return;
					}
				}
			}
		} else {
			dfs(x, y + 1);
		}
	}
}