import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int tc = 1; tc < t + 1; tc++) {
			int n = sc.nextInt();
			int l = sc.nextInt();

			int[] taste = new int[n];
			int[] cal = new int[n];

			for (int i = 0; i < n; i++) {
				taste[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}

			int[][] dp = new int[2][l + 1];

			for (int i = 0; i < n; i++) {
				for (int j = 1; j <= l; j++) {
					if (j >= cal[i]) {
						dp[1][j] = Math.max(dp[0][j], dp[0][j - cal[i]] + taste[i]);
					} else {
						dp[1][j] = dp[0][j];
					}
				}
				
				for (int j = 1; j <= l; j++) {
					dp[0][j] = dp[1][j];
				}
			}

			System.out.printf("#%d %d\n", tc, dp[1][l]);
		}
	}
}