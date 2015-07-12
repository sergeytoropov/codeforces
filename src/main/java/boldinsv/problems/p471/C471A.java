package boldinsv.problems.p471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C471A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] params = reader.readLine().split(" ");
		reader.close();

		int[] parts = new int[10];
		for (int i = 0; i < params.length; i++) {
			parts[Integer.parseInt(params[i])] += 1;
		}

		final int stateInit = 0;
		final int stateExit = 1;
		final int stateAlien = 2;
		final int stateBear = 3;
		final int stateElephant = 4;
		int state = stateInit;

		String answer = "";
		boolean isStoped = false;
		while (!isStoped) {

			switch (state) {
				case stateInit:
					state = stateAlien;
					for (int i = 1; i < parts.length; i++) {
						if (parts[i] == 4 || parts[i] == 5) {
							state = stateBear;
							break;
						} else if (parts[i] == 6) {
							state = stateElephant;
							break;
						}
					}
					break;

				case stateExit:
					isStoped = true;
					break;

				case stateAlien:
					answer = "Alien";
					state = stateExit;
					break;

				case stateBear:
					answer = "Bear";
					state = stateExit;

					for (int i = 1; i < parts.length; i++) {
						if (parts[i] == 2) {
							answer = "";
							state = stateElephant;
							break;
						}
					}
					break;

				case stateElephant:
					answer = "Elephant";
					state = stateExit;
					break;
			}
		}
		System.out.println(answer);
	}
}