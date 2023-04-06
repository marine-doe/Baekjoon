import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, map2;
	static boolean[][] visited, vst;
	static int[] row = { 0, 1, 0, -1 }, col = { 1, 0, -1, 0 };
	static int n, m, result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		map2 = new int[n][m];

		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = Character.getNumericValue(line[j]);
				map2[i][j] = Character.getNumericValue(line[j]);
			}
		}

		visited = new boolean[n][m];
		bfs(0, 0, 2);
		vst = new boolean[n][m];
		bfsE(n - 1, m - 1, -1);

		findResult();

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	private static void findResult() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					List<Integer> arr1 = new ArrayList<>();
					List<Integer> arr2 = new ArrayList<>();
					for (int d = 0; d < 4; d++) {
						int nr = i + row[d];
						int nc = j + col[d];
						if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
							if (map[nr][nc] > 1) {
								arr1.add(map[nr][nc]);
							}
							if (map2[nr][nc] < 0) {
								arr2.add(map2[nr][nc]);
							}
						}
					}
					for (int r = 0; r < arr1.size(); r++) {
						for (int c = 0; c < arr2.size(); c++) {
							int dist = arr1.get(r) - arr2.get(c);
							result = Math.min(result, dist);
						}
					}
				}
			}
		}
	}

	private static void bfsE(int x, int y, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		if (map2[x][y] == 1) {
			q.offer(new int[] { x, y, cnt });
		} else {
			q.offer(new int[] { x, y, cnt });
		}
		vst = new boolean[n][m];
		vst[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			map2[cur[0]][cur[1]] = cur[2];

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + row[i];
				int nc = cur[1] + col[i];
				if (nr >= 0 && nc >= 0 && nr < n && nc < m && !vst[nr][nc]) {
					if (map2[nr][nc] == 0) {
						q.offer(new int[] { nr, nc, cur[2] - 1 });
						vst[nr][nc] = true;
					}
				}
			}
		}
	}

	private static void bfs(int x, int y, int cnt) {
		Queue<int[]> q = new LinkedList<>();

		if (map[x][y] == 1) {
			q.offer(new int[] { x, y, cnt });
		} else {
			q.offer(new int[] { x, y, cnt });
		}

		visited = new boolean[n][m];
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			map[cur[0]][cur[1]] = cur[2];

			if (cur[0] == n - 1 && cur[1] == m - 1) {
				result = Math.min(result, cur[2]) - 1;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + row[i];
				int nc = cur[1] + col[i];
				if (nr >= 0 && nc >= 0 && nr < n && nc < m && !visited[nr][nc]) {
					if (map[nr][nc] == 0) {
						q.offer(new int[] { nr, nc, cur[2] + 1 });
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
}