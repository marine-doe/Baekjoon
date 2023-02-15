import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[][] arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int sum = 0;
			for (int i = 0; i < n; i++) {
				int count = 0;
				for (int d = 0; d < n; d++) {
					// 가로 체크
					if (arr[i][d] == 1) {
						count++;
					}else {
						if(count == k) {
							sum++;
						}
						count = 0;
					}
				}
				if(count == k) {
					sum++;
				}
				count = 0;
				for (int d = 0; d < n; d++) {
					// 세로 체크
					if (arr[d][i] == 1) {
						count++;
					}else {
						if(count == k) {
							sum++;
						}
						count = 0;
					}
				}
				if(count == k) {
					sum++;
				}
			}

			System.out.printf("#%d %d\n", tc, sum);
		}
	}
}
