package boldinsv.problems.p454;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C454A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		reader.close();

		for (int idx = 1; idx <= n; idx += 2) {
			show(n, idx);
		}

		for (int idx = n - 2; idx >= 1; idx -= 2) {
			show(n, idx);
		}
	}

	private static void show(int n, int idx) {
		int begin = ((n - idx) / 2) + 1;
		int end = begin + idx;
		for (int j = 1; j <= n; j++) {
			if (j >= begin && j < end) {
				System.out.print("D");
			} else {
				System.out.print("*");
			}
		}
		System.out.println();
	}
}