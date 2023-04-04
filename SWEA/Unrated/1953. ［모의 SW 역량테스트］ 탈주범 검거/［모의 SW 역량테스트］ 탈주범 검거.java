import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static class possibility {
		int x, y, d;

		public possibility(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.d = depth;
		}
	}

	static Queue<possibility> q;
	static boolean[][] visited;
	static int[][] map;
	static int n, m, l;

	private static void bfs(int x, int y) {
		q = new LinkedList<>();
		q.offer(new possibility(x, y, 1));

		while (!q.isEmpty()) {
			possibility path = q.poll();

			if (path.d > l) {
				continue;
			}

			visited[path.x][path.y] = true;

			switch (map[path.x][path.y]) {
			case 1: // 상하좌우
				UP(path);
				DOWN(path);
				LEFT(path);
				RIGHT(path);
				break;
			case 2: // 상하
				UP(path);
				DOWN(path);
				break;
			case 3: // 좌우
				LEFT(path);
				RIGHT(path);
				break;
			case 4: // 상우
				UP(path);
				RIGHT(path);
				break;
			case 5: // 하우돈
				DOWN(path);
				RIGHT(path);
				break;
			case 6: // 하좌
				DOWN(path);
				LEFT(path);
				break;
			case 7: // 상좌
				UP(path);
				LEFT(path);
				break;
			}
		}
	}

	private static void RIGHT(possibility path) {
		if (path.y + 1 < m && !visited[path.x][path.y + 1]) {
			switch (map[path.x][path.y + 1]) {
			case 1:
				q.offer(new possibility(path.x, path.y + 1, path.d + 1));
				break;
			case 3:
				q.offer(new possibility(path.x, path.y + 1, path.d + 1));
				break;
			case 6:
				q.offer(new possibility(path.x, path.y + 1, path.d + 1));
				break;
			case 7:
				q.offer(new possibility(path.x, path.y + 1, path.d + 1));
				break;
			}
		}
	}

	private static void LEFT(possibility path) {
		if (path.y - 1 >= 0 && !visited[path.x][path.y - 1]) {
			switch (map[path.x][path.y - 1]) {
			case 1:
				q.offer(new possibility(path.x, path.y - 1, path.d + 1));
				break;
			case 3:
				q.offer(new possibility(path.x, path.y - 1, path.d + 1));
				break;
			case 4:
				q.offer(new possibility(path.x, path.y - 1, path.d + 1));
				break;
			case 5:
				q.offer(new possibility(path.x, path.y - 1, path.d + 1));
				break;
			}
		}
	}

	private static void DOWN(possibility path) {
		if (path.x + 1 < n && !visited[path.x + 1][path.y]) {
			switch (map[path.x + 1][path.y]) {
			case 1:
				q.offer(new possibility(path.x + 1, path.y, path.d + 1));
				break;
			case 2:
				q.offer(new possibility(path.x + 1, path.y, path.d + 1));
				break;
			case 4:
				q.offer(new possibility(path.x + 1, path.y, path.d + 1));
				break;
			case 7:
				q.offer(new possibility(path.x + 1, path.y, path.d + 1));
				break;
			}
		}
	}

	private static void UP(possibility path) {
		if (path.x - 1 >= 0 && !visited[path.x - 1][path.y]) {
			switch (map[path.x - 1][path.y]) {
			case 1:
				q.offer(new possibility(path.x - 1, path.y, path.d + 1));
				break;
			case 2:
				q.offer(new possibility(path.x - 1, path.y, path.d + 1));
				break;
			case 5:
				q.offer(new possibility(path.x - 1, path.y, path.d + 1));
				break;
			case 6:
				q.offer(new possibility(path.x - 1, path.y, path.d + 1));
				break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 1; tc < t + 1; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			int x = sc.nextInt(); // 초기 좌표 x, y
			int y = sc.nextInt();
			l = sc.nextInt(); // 도망친 시간
			map = new int[n][m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			visited = new boolean[n][m];
			bfs(x, y);

			int result = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (visited[i][j]) {
						result++;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}