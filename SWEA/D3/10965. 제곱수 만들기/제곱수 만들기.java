import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int max = (int) Math.pow(10, 7) + 1;
		List<Integer> primeArr = new ArrayList<>();
		
		int sqrt = (int) Math.sqrt(max) + 1;
		boolean[] isPrime = new boolean[sqrt + 1];
		for(int i = 2; i <= sqrt; i++) {
			if(isPrime[i]) continue;
			primeArr.add(i);
			for(int j = 1; j*i <= sqrt; j++) {
				isPrime[j*i] = true;
			}
		}
		
		List<Integer> sqrArr = new ArrayList<>();
		for(Integer i : primeArr) {
			sqrArr.add(i*i);
		}

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int num = Integer.parseInt(br.readLine());

			int result = 1;

			for (int i = 0; i <= sqrt; i++) {
				int divisor = sqrArr.get(i);
				if(num < divisor) {
					break;
				}
				for(;;) {
					if(num % divisor == 0) {
						num /= divisor;
					}else {
						break;
					}
				}
			}
			
			bw.write("#" + tc + " " + num + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}