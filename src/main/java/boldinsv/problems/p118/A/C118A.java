package boldinsv.problems.p118.A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C118A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine().toLowerCase();
		reader.close();

		char[] charsA = {'a', 'o', 'y', 'e', 'u', 'i'};

		StringBuffer sBuf = new StringBuffer("");
		for (int index = 0; index < line.length(); index++) {

			boolean isCharsA = false;
			for (int j = 0; j < charsA.length; j++) {
				if (line.charAt(index) == charsA[j]) {
					isCharsA = true;
					break;
				}
			}

			if (!isCharsA) {
				sBuf.append('.').append(line.charAt(index));
			}
		}
		System.out.println(sBuf.toString());
	}
}