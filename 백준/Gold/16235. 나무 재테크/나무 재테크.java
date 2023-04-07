import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Tree implements Comparable<Tree> {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	static PriorityQueue<Tree> tree;
	static Queue<Tree> temp, deadTree;
	static int[][] map, nut;
	static int[] row = { -1, -1, 0, 1, 1, 1, 0, -1 }, col = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int n, m, k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n + 1][n + 1];
		nut = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n + 1; j++) {
				nut[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		tree = new PriorityQueue<>();
		deadTree = new LinkedList<>();
		temp = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			tree.offer(new Tree(x, y, age));
		}

		int year = 0;
		while (year < k && tree.size() != 0) {
			Spring();
			Summer();
			Fall();
			Winter();
			year++;
		}

		System.out.println(tree.size());
	}

	private static void Spring() { // 양분을 먹는 계절
		while (!tree.isEmpty()) { // 나무 큐 순회
			Tree cur = tree.poll(); // 어린 순으로 뽑힌다
			if (map[cur.x][cur.y] - cur.age < -5) { // 해당 자리와 나이 비교
				deadTree.offer(cur); // 영양분이 부족하면 죽은 나무 큐에 넣는다
			} else { // 부족하지 않으면
				map[cur.x][cur.y] -= cur.age++; // 해당 자리를 나이만큼 감소 후 1 증가
				temp.offer(cur); // 임시 큐에 넣는다
			}
		}
	}

	private static void Summer() { // 죽은 나무가 양분이 되는 계절
		while (!deadTree.isEmpty()) { // 죽은 나무 큐 순회
			Tree cur = deadTree.poll(); // 아무렇게나 뽑아도 상관 없다
			map[cur.x][cur.y] += cur.age / 2; // 나무의 나이의 절반만큼 영양분이 된다.
		}
	}

	private static void Fall() { // 나무가 자식을 낳는 계절
		while (!temp.isEmpty()) { // 임시 큐 순회
			Tree cur = temp.poll();
			if (cur.age % 5 == 0) { // 나이가 5의 배수라면
				for (int d = 0; d < 8; d++) { // 8방 순회
					int nr = cur.x + row[d];
					int nc = cur.y + col[d];
					if (nr >= 1 && nc >= 1 && nr <= n && nc <= n) { // 범위 내라면
						tree.offer(new Tree(nr, nc, 1)); // 자식을 생성하여 나무 큐에 넣는다.
					}
				}
			}
			tree.offer(cur); // 나이에 상관 없이 다시 나무 큐에 넣는다.
		}
	}

	private static void Winter() {
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				map[i][j] += nut[i][j]; // 나무에 영양분을 더해준다.
			}
		}
	}
}