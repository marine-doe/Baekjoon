import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int tc = 1; tc < t + 1; tc++) {
			int n = sc.nextInt();
			int l = sc.nextInt();

			int[] taste = new int[n + 1];
			int[] cal = new int[n + 1];

			for (int i = 1; i <= n; i++) {
				taste[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}

			int[][] dp = new int[n + 1][l + 1];

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= l; j++) {
					if (j >= cal[i]) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cal[i]] + taste[i]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

			System.out.printf("#%d %d\n", tc, dp[n][l]);
		}
	}
}