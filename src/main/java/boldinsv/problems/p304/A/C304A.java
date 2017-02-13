package boldinsv.problems.p304.A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.stream.IntStream;

public class C304A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		reader.close();

		int answer = IntStream
				.rangeClosed(1, n)
				.map(a ->
					IntStream
							.rangeClosed(a, n)
							.filter(b -> {
								int sum = a * a + b * b;
								int sqrt = (int) Math.sqrt(sum);

								if (sqrt > n) {
									return false;
								}
								return sqrt * sqrt == sum;
							})
							.map(b -> 1)
							.sum())
				.sum();

		System.out.println("" + answer);
	}
}