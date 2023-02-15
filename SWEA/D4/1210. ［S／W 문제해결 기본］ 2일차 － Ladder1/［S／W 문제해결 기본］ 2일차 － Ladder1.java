import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 0; tc < 10; tc++) {
			int t = Integer.parseInt(br.readLine());
			// 사다리 양끝에 공간을 1씩 더 두자
			int[][] ladder = new int[100][102];
			
			// 2를 받았을 때의 좌표찍기 
			int x = 0; int y = 0;
			
			boolean up = true;
			
			// 사다리 맹글기
			for(int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j < 101; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if(ladder[i][j] == 2) {
						x = i; y = j;
					}
				}
			}
			
			for(;;) {
				// 위로 가긔
				if(up) {
					x--;
					// 양 옆을 만나면 up을 false로 두긔
					if(ladder[x][y+1] == 1 || ladder[x][y-1] == 1) {
						up = false;
					}
				}else { // 옆으로 가긔
					if(ladder[x][y+1] == 1) {
						for(;;) {
							y++;
							if(ladder[x][y+1] == 0) {
								up = true;
								break;
							}
						}
					}else if(ladder[x][y-1] == 1) {
						for(;;) {
							y--;
							if(ladder[x][y-1] == 0) {
								up = true;
								break;
							}
						}
					}
				}
				if(x == 0) {
					break;
				}
			}
			
			int start = y - 1;
			
			// 출력
			System.out.printf("#%d %d\n", t, start);
		}
	}
}