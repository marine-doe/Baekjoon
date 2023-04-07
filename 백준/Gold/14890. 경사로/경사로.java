import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, l, result = 0;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		l = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			rowCheck(i);
			colCheck(i);
		}
		
		System.out.println(result);
	}

	private static void rowCheck(int x) {
		visited = new boolean[n][n];
		for (int i = 0; i < n - 1; i++) {
			if (map[x][i] == map[x][i + 1]) { // 평지면 그냥 계속
				continue;
			} else if (map[x][i] - map[x][i + 1] == 1) { // 내리막
				if (i + 1 + l > n) { // 경사로를 놓을 칸이 부족하면 그냥 불가능한 걸루
					return;
				}
				for (int j = 1; j <= l; j++) { // 평지여야하고 아직 경사로를 안놨어야함
					if (map[x][i + 1] != map[x][i + j] || visited[x][i + j]) {
						return;
					}
				}
				for (int j = 1; j <= l; j++) { // 경사로를 놓는 작업
					visited[x][i + j] = true;
				}
			} else if (map[x][i] - map[x][i + 1] == -1) { // 오르막
				if (i + 1 - l < 0) { // 경사로를 놓을 칸이 부족하면 그냥 불가능한 걸루
					return;
				}
				for (int j = 0; j < l; j++) { // 평지여야하고 아직 경사로를 안놨어야함
					if (map[x][i] != map[x][i - j] || visited[x][i - j]) {
						return;
					}
				}
				for (int j = 0; j < l; j++) {
					visited[x][i - j] = true;
				}
			} else {
				return;
			}
		}
		result++;
	}

	private static void colCheck(int y) {
		visited = new boolean[n][n];
		for (int i = 0; i < n - 1; i++) {
			if (map[i][y] == map[i + 1][y]) { // 평지면 그냥 계속
				continue;
			} else if (map[i][y] - map[i + 1][y] == 1) { // 내리막
				if (i + 1 + l > n) { // 경사로를 놓을 칸이 부족하면 그냥 불가능한 걸루
					return;
				}
				for (int j = 1; j <= l; j++) { // 평지여야하고 아직 경사로를 안놨어야함
					if (map[i + 1][y] != map[i + j][y] || visited[i + j][y]) {
						return;
					}
				}
				for (int j = 1; j <= l; j++) { // 경사로를 놓는 작업
					visited[i + j][y] = true;
				}
			} else if (map[i][y] - map[i + 1][y] == -1) { // 오르막
				if (i + 1 - l < 0) { // 경사로를 놓을 칸이 부족하면 그냥 불가능한 걸루
					return;
				}
				for (int j = 0; j < l; j++) { // 평지여야하고 아직 경사로를 안놨어야함
					if (map[i][y] != map[i - j][y] || visited[i - j][y]) {
						return;
					}
				}
				for (int j = 0; j < l; j++) {
					visited[i - j][y] = true;
				}
			} else {
				return;
			}
		}
		result++;
	}
}