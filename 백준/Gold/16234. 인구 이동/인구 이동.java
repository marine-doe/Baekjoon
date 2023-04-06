import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static List<int[]> location;
	static boolean[][] visited;
	static boolean isExist, isMove;
	static int[][] map;
	static int[] row = { -1, 1, 0, 0 }, col = { 0, 0, -1, 1 };
	static int n, left, right, result = 0;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visited = new boolean[n][n]; // 인구 이동을 한 곳에서 또 하면 안되니깐 방문처리
			isMove = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					isExist = false;
					if (!visited[i][j])
						bfs(i, j);
					if (isExist) { // 연합을 했으면
						pomo(); // 인구 이동
						isMove = true;
					}
				}
			}
			if (isMove) { // 인구 이동이 있으면
				result++;
			} else {
				break;
			}
		}

		System.out.println(result);
	}

	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		location = new ArrayList<>();

		q.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			location.add(new int[] { cur[0], cur[1] });

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + row[d];
				int nc = cur[1] + col[d];

				if (nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
					int diff = Math.abs(map[nr][nc] - map[cur[0]][cur[1]]);

					if (diff >= left && diff <= right) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
						isExist = true; // 연합을 했다고 볼 수 있다
					}
				}
			}
		}
	}

	private static void pomo() {
		int sum = 0; // 인구수의 총합
		for (int i = 0; i < location.size(); i++) {
			sum += map[location.get(i)[0]][location.get(i)[1]];
		}

		int diff = sum / location.size();

		for (int i = 0; i < location.size(); i++) {
			map[location.get(i)[0]][location.get(i)[1]] = diff;
		}
	}
}