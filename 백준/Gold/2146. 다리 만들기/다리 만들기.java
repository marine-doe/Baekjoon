import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Bridge {
		int x, y, dist, num;

		public Bridge(int x, int y, int dist, int num) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.num = num;
		}
	}

	static boolean[][] visited;
	static boolean flag;
	static int[][] map;
	static int[] row = { 1, -1, 0, 0 }, col = { 0, 0, -1, 1 };
	static int n, idx = 1, result = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				flag = false;
				dfs(i, j);
				if (flag) {
					idx++;
				}
			}
		}

		outer: for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nr = i + row[d];
						int nc = j + col[d];
						if (nr < n && nc < n && nr >= 0 && nc >= 0 && map[nr][nc] == 0) {
							bfs(nr, nc, map[i][j]); // 좌표와 다리 길이, 붙어있는 해안의 번호
						}
					}
				}

				if (result == 1) {
					break outer;
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		System.out.println(result);
	}

	private static void bfs(int x, int y, int num) {
		visited = new boolean[n][n];
		Queue<Bridge> q = new LinkedList<>();

		q.offer(new Bridge(x, y, 1, 0));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Bridge cur = q.poll();

			if (cur.num != 0) { // 0이 아니면 다리를 지을 수 있다
				result = Math.min(result, cur.dist - 1);
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.x + row[d];
				int nc = cur.y + col[d];
				if (nr < n && nc < n && nr >= 0 && nc >= 0) {
					if (map[nr][nc] != num && !visited[nr][nc]) { // 같은 땅이어도 안되고 방문한 곳도 안됨
						q.offer(new Bridge(nr, nc, cur.dist + 1, map[nr][nc]));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

	private static void dfs(int x, int y) {
		if (x < 0 || y < 0 || x >= n || y >= n || map[x][y] == 0) {
			return;
		}

		if (map[x][y] == 1 && !visited[x][y]) {
			visited[x][y] = true;
			flag = true;
			map[x][y] = idx;
			for (int d = 0; d < 4; d++) {
				int nr = x + row[d];
				int nc = y + col[d];
				dfs(nr, nc);
			}
		}
	}
}