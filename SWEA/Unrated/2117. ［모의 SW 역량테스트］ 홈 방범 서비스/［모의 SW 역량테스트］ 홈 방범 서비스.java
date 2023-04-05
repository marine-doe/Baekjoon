import java.util.*;

public class Solution {
	static List<int[]> home;
	static int n, m, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 1; tc < t + 1; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();

			home = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int info = sc.nextInt();
					if (info == 1) { // 집 정보를 저장하자
						home.add(new int[] { i, j });
					}
				}
			}

			result = 0;
			for (int k = 1; k < n + 2; k++) { // 운영비용
				if(k > home.size()) break;
				for (int i = 0; i < n; i++) { // (i, j)는 서비스를 제공하는 중심
					for (int j = 0; j < n; j++) {
						makeMoney(i, j, k);
					}
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}

	private static void makeMoney(int x, int y, int k) {
		int sum = 0;
		for (int i = 0; i < home.size(); i++) {
			int dist = Math.abs(x - home.get(i)[0]) + Math.abs(y - home.get(i)[1]);
			if (k > dist) {
				sum++;
			}
		}
		int profit = m * sum; // 이익
		int cost = k * k + (k - 1) * (k - 1); // 운영비용
		if (profit >= cost) { // 손해는 안봐
			result = Math.max(result, sum);
		}
	}
}