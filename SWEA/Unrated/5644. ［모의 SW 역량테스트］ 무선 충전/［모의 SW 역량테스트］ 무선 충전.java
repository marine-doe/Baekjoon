import java.util.Scanner;

public class Solution {
	static class bat { // x, y좌표 바꿈
		int y, x, c, p;

		public bat(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
	}

	static bat[] battery;
	static boolean[] visited1, visited2;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 1; tc < t + 1; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();

			int[] ma = new int[n + 1]; // A의 행동
			int[] mb = new int[n + 1]; // B의 행동

			for (int i = 0; i < n; i++) {
				ma[i] = sc.nextInt();
			}
			for (int i = 0; i < n; i++) {
				mb[i] = sc.nextInt();
			}

			battery = new bat[m];
			for (int i = 0; i < m; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int c = sc.nextInt();
				int p = sc.nextInt();
				battery[i] = new bat(x, y, c, p);
			}

			int time = 0, result = 0;
			int x1 = 1, y1 = 1, x2 = 10, y2 = 10;
			while (time <= n) {
				int[][] dist = new int[2][m];

				visited1 = new boolean[m];
				visited2 = new boolean[m];

				for (int i = 0; i < m; i++) {
					dist[0][i] = Math.abs(x1 - battery[i].x) + Math.abs(y1 - battery[i].y);
					dist[1][i] = Math.abs(x2 - battery[i].x) + Math.abs(y2 - battery[i].y);
					if (battery[i].c >= dist[0][i]) {
						visited1[i] = true;
					}
					if (battery[i].c >= dist[1][i]) {
						visited2[i] = true;
					}
				}

				max = 0;
				CBC(0, 0, 0, 0);
				result += max;

				switch (ma[time]) {
				case 1:
					x1--;
					break;
				case 2:
					y1++;
					break;
				case 3:
					x1++;
					break;
				case 4:
					y1--;
					break;
				default:
					break;
				}
				switch (mb[time]) {
				case 1:
					x2--;
					break;
				case 2:
					y2++;
					break;
				case 3:
					x2++;
					break;
				case 4:
					y2--;
					break;
				default:
					break;
				}

				time++;
			}

			System.out.printf("#%d %d\n", tc, result);
		}
	}

	private static void CBC(int cnt, int sum, int max1, int max2) {
		if (cnt == battery.length) {
			max = Math.max(sum, max);
			return;
		}

		if (visited1[cnt] && visited2[cnt]) { // T T
			int temp = max1;
			if (battery[cnt].p > max1) {
				max1 = battery[cnt].p;
			}
			CBC(cnt + 1, max1 + max2, max1, max2);
			max1 = temp;
			if (battery[cnt].p > max2) {
				max2 = battery[cnt].p;
				CBC(cnt + 1, max1 + max2, max1, max2);
			}
		} else if (visited1[cnt] && !visited2[cnt]) { // T F
			if (battery[cnt].p > max1) {
				max1 = battery[cnt].p;
			}
			CBC(cnt + 1, max1 + max2, max1, max2);
		} else if (!visited1[cnt] && visited2[cnt]) { // F T
			if (battery[cnt].p > max2) {
				max2 = battery[cnt].p;
			}
			CBC(cnt + 1, max1 + max2, max1, max2);
		} else { // F F
			CBC(cnt + 1, max1 + max2, max1, max2);
		}
	}
}