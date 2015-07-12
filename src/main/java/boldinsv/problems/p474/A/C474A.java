package boldinsv.problems.p474.A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C474A {
	private static enum ToGo {LEFT, RIGHT};

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ToGo togo = ToGo.LEFT;
		if ("R".equals(reader.readLine())) {
			togo = ToGo.RIGHT;
		} 
		char[] text = reader.readLine().toCharArray();
		reader.close();

		char[][] chars = {
			{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
			{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';'},
			{'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}
		};
		int length = 10;

		int start = 0;
		int end = 0;
		int delta = 0;
		if (togo == ToGo.LEFT) {
			start = 0;
			end = length - 1;
			delta = 1;
		} else if (togo == ToGo.RIGHT) {
			start = 1;
			end = length;
			delta = -1;
		};

		for (int index = 0; index < text.length; index++) {

			boolean isBreak = false;
			for (int i = 0; i < chars.length; i++) {
				for (int j = start; j < end; j++) {
	
					if (text[index] == chars[i][j]) {
						text[index] = chars[i][j + delta];
						isBreak = true;
						break;
					}	
				}
				if (isBreak) {
					break;
				}
			}
		}

		StringBuffer answer = new StringBuffer("");
		for (int index = 0; index < text.length; index++) {
			answer.append(text[index]);
		}
		
		System.out.println(answer.toString());
	}
}