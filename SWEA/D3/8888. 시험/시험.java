import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 시험 치는 사람 수
			int N = Integer.parseInt(st.nextToken());
			// 참가 번호 별로 점수 입력
			int[][] scoreAndSolved = new int[2][N]; 
			// 문제 개수
			int T = Integer.parseInt(st.nextToken());
			int[] testScore = new int[T];
			// 시험 결과 배열 
			int[][] testResult = new int[N][T];
			// 내 친구 지학이의 참가번호
			int idx = Integer.parseInt(st.nextToken()) - 1;
			// 시험 결과 배열 받기
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < T; j++) {
					testResult[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 문제마다 점수 매기기
			for(int i = 0; i < T; i++) {
				for(int j = 0; j < N; j++) {
					if(testResult[j][i] == 0) {
						testScore[i]++;
					}
				}
			}
			
			// 참가 번호 별 점수 입력 + 푼 문제 저장
			for(int i = 0; i < N; i++) {
				int sum = 0;
				int count = 0;
				for(int j = 0; j < T; j++) {
					if(testResult[i][j] == 1) {
						sum += testScore[j];
						count++;
					}
				}
				scoreAndSolved[0][i] = sum; // 점수 저장
				scoreAndSolved[1][i] = count; // 푼 문제수 저장
			}

			// 지학이 등수 구하기
			int rank = 1;
			for(int i = 0; i < N; i++) {
				if(i != idx) {// 지학이랑 빼고 비교
					if(scoreAndSolved[0][i] > scoreAndSolved[0][idx]) {// 점수가 높으면
						rank++;
					}else if(scoreAndSolved[0][i] == scoreAndSolved[0][idx]) {// 점수가 같으면
						if(scoreAndSolved[1][i] > scoreAndSolved[1][idx]) {// 푼 문제가 많으면
							rank++;
						}else if(scoreAndSolved[1][i] == scoreAndSolved[1][idx]) {// 다 같으면
							if(i < idx) {
								rank++;
							}
						}
					}
				}
			}
			
			bw.write("#" + tc + " " + scoreAndSolved[0][idx] + " " + rank + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}