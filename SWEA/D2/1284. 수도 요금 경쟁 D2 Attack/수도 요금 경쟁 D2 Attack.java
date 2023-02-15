import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// A사 리터당 p원
			int p = Integer.parseInt(st.nextToken());

			// B사 월 사용량 r 이하면 기본요금 q, 초과하면 리터당 s
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			// 종민이는 한 달에 w 만큼 쓴다.
			int w = Integer.parseInt(st.nextToken());
			
			int A = p * w;
			int B = 0;
			if (r >= w) {
				B = q;
			} else {
				B = q + (w - r) * s;
			}
			
			if(A > B) {
				System.out.printf("#%d %d\n", tc, B);
			}else {
				System.out.printf("#%d %d\n", tc, A);
			}
		}
	}
}