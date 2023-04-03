import java.io.*;
import java.util.*;

public class Solution {
	static boolean[][] visited;
	static int[] row = { 1, -1, 0, 0 }, col = { 0, 0, 1, -1 };
	static int n, w, h, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < t + 1; tc++) {
			StringBuilder sb = new StringBuilder("#" + tc + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int[][] map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = Integer.MAX_VALUE;
			dfs(0, 0, map);

			sb.append(result + "");
			System.out.println(sb);
		}
	}

	private static void dfs(int y, int cnt, int[][] map) {
		if (cnt == n) {
			int sum = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] > 0) {
						sum++;
					}
				}
			}
			result = Math.min(result, sum);
			return;
		}

		if (result == 0) {
			return;
		}

		int[][] clone = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				clone[i][j] = map[i][j];
			}
		}

		boolean flag = false;

		for (int i = 0; i < h; i++) {
			if (clone[i][y] > 0) {
				visited = new boolean[h][w];
				bb(i, y, clone);
				flag = true;
				break;
			}
		}
		if (flag) {
			collapse(clone);
		}

		dfs(0, cnt + 1, clone);
		if (y < w - 1) {
			dfs(y + 1, cnt, map);
		}
	}

	private static void collapse(int[][] clone) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (visited[i][j]) {
					clone[i][j] = 0;
				}
			}
		}

		for (int j = 0; j < w; j++) {
			for (int i = h - 1; i >= 0; i--) {
				if (clone[i][j] == 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (clone[k][j] > 0) {
							clone[i][j] = clone[k][j];
							clone[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}

	private static void bb(int x, int y, int[][] clone) {
		int dist = clone[x][y];
		clone[x][y] = 0;
		visited[x][y] = true;
		for (int k = 1; k < dist; k++) {
			for (int d = 0; d < 4; d++) {
				int nr = x + row[d] * k;
				int nc = y + col[d] * k;
				if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc] && clone[nr][nc] > 0) {
					bb(nr, nc, clone);
				}
			}
		}
	}
}