package boldinsv.problems.p525.B;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeMap;


public class PashaAndString {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		char [] charArray = input.toCharArray();
		
		br.readLine();
		
		String [] poses = br.readLine().split("[ ]+");
		
		int [] inverts = new int [input.length() / 2];
		
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		int min = inverts.length;
		
		for (int i = 0; i < poses.length; i++) {
			int pos = Integer.parseInt(poses[i]) - 1;
			inverts[pos]++;
			if (pos < min) {
				min = pos;
			}
		}

		boolean flag = false;
		for (int i = min; i < inverts.length; i++) {
			flag = !flag & inverts[i] % 2 == 1 || inverts[i] % 2 == 0 & flag;
			if (flag) {
				int other = input.length() - i - 1;
				char temp = charArray[i];
				charArray[i] = charArray[other];
				charArray[other] = temp;
			}
		}
		
		System.out.println(new String(charArray));
	}

}