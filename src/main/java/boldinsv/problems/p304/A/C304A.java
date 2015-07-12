package boldinsv.problems.p304.A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C304A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		reader.close();

		int answer = 0;
		for (int a = 1; a <= n; a++) {
			for (int b = a; b <= n; b++) {

				int sum = a * a + b * b;
				int sqrt = (int) Math.sqrt(sum);

				if (sqrt > n) {
					break;
				}

				if (sqrt * sqrt == sum) {
					answer += 1;
				}
			}
		}
		System.out.println("" + answer);
	}
}