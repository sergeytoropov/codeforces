package boldinsv.problems.p384;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class C384A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		reader.close();

		int[][] matrix = new int[n][n];

		int coder = IntStream
				.range(0, matrix.length)
				.map(i -> IntStream
							.range(0, matrix[i].length)
							.filter(j -> (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))
							.map(j -> {matrix[i][j] = 1; return 1;})
							.sum())
				.sum();

		System.out.println("" + coder);

		IntStream
				.range(0, matrix.length)
				.mapToObj(i -> new String(
						IntStream
							.range(0, matrix[i].length)
							.mapToObj(j -> matrix[i][j] == 1 ? "C" : ".")
							.collect(Collectors.joining())))
				.forEach(System.out::println);
	}
}