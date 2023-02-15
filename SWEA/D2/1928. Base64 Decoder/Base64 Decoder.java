import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			String text = br.readLine();
			
			byte[] targetBytes = text.getBytes();
			
			Decoder dc = Base64.getDecoder();
			byte[] decodedBytes = dc.decode(targetBytes);
			
			System.out.printf("#%d %s\n", tc, new String(decodedBytes));
		}
	}
}
