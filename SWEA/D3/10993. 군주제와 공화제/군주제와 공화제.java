import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[] X = new int[n + 1];
			int[] Y = new int[n + 1];
			int[] S = new int[n + 1];
			String[] result = new String[n + 1];
			boolean[] Democracy = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				X[i] = Integer.parseInt(st.nextToken());
				Y[i] = Integer.parseInt(st.nextToken());
				S[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= n; i++) { // 자기 자신
				int count = 0;
				int idx = 0;
				double max = 0;
				for (int j = 1; j <= n; j++) { // 다른 도시
					if (i == j) { // 자기 자신 제외
						continue;
					} else {
						// j 도시와 자기 자신인 i도시와의 거리
						double dist = Math.pow((X[j] - X[i]), 2) + Math.pow((Y[j] - Y[i]), 2);
						double jForce = S[j] / dist;
						if (jForce > S[i]) {
							// 영향력이 가장 큰 도시를 저장하면 됨
							count++;
							if (jForce > max) {
								idx = j;
								max = jForce;
								Democracy[i] = false;
							} else if (jForce != 0 && jForce == max) {
								Democracy[i] = true;
							}
						}
					}
				}
				// 다른 도시의 영향을 받았는지 체크
				if (count >= 1) {
					if (Democracy[i]) {
						result[i] = "D";
					} else {
						result[i] = String.valueOf(idx);
					}
				} else {
					result[i] = "K";
				}
			}
			// D, K 둘 다 아니면 가리키는 애가 D, K인지 검사 후 D, K가 아니면 따라가기
			for (int i = 1; i <= n; i++) {
				result[i] = FindIndex(result, i);
			}

			bw.write("#" + tc);
			for (int i = 1; i <= n; i++) {
				bw.write(" " + result[i]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// 재귀함수
	static int cnt = 0;

	private static String FindIndex(String[] result, int i) {
		if (result[i].equals("D") || result[i].equals("K")) {
			if (cnt > 0) {
				cnt = 0;
				return String.valueOf(i);
			} else {
				return result[i];
			}
		} else {
			cnt++;
			return FindIndex(result, Integer.parseInt(result[i]));
		}
	}
}
