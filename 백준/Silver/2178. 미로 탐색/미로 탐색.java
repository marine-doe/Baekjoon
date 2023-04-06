import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] maze;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maze = new int[n][m];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				maze[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}
		
		bfs(0, 0); // 시작점 input

		System.out.printf("%d", maze[n - 1][m - 1]);
	}

	static int count = 1;

	private static void bfs(int x, int y) {
		Queue<Integer> qx = new LinkedList<>(); // x좌표
		Queue<Integer> qy = new LinkedList<>(); // y좌표
		qx.offer(x); qy.offer(y); // x좌표와 y좌표를 넣고 시작한다.
		while(!qx.isEmpty() && !qy.isEmpty()) { // 큐가 비어있으면 탈출
			x = qx.poll(); y = qy.poll(); // 일단 디큐
			for(int i = 0; i < 4; i++) { // 4방 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) { // 범위 밖이면 무시
					continue;
				}
				
				if(maze[nx][ny] == 0) { // 0이어도 무시
					continue;
				}
				
				if(maze[nx][ny] == 1) { // 1을 만나면 
					maze[nx][ny] = maze[x][y] + 1; // 이전 좌표에서 +1한 값을 저장
					qx.offer(nx); qy.offer(ny); // 그리고 q에 삽입
				}
			}
		}
	}
}