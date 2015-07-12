package boldinsv.problems.p479.A;

import java.util.Scanner;
import java.io.IOException;

public class C479A {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		scanner.close();

		int answer = 0;
		if (a + b + c > answer) {
			answer = a + b + c;
		}
		if (a * b * c > answer) {
			answer = a * b * c;
		}
		if ((a + b) * c > answer) {
			answer = (a + b) * c;
		}
		if (a * (b + c) > answer) {
			answer = a * (b + c);
		}
		System.out.println("" + answer);
	}
}