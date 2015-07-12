package boldinsv.problems.p384;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuffer;

public class C384A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		reader.close();

		int[][] matrix = new int[n][n];

		int coder = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if ( (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0) ) {
					matrix[i][j] = 1;
					coder += 1;
				}
			}
		}
		System.out.println("" + coder);

		for (int i = 0; i < matrix.length; i++) {
			StringBuffer sBuf = new StringBuffer();
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					sBuf.append("C");
				} else {
					sBuf.append(".");
				}
			}
			System.out.println(sBuf);
		}
	}
}