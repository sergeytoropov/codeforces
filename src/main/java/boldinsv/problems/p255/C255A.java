package boldinsv.problems.p255;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C255A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] params = reader.readLine().split(" ");
		reader.close();

		int[] tasks = new int[n];
		int index = 0;
		for (String param: params) {
			tasks[index] = Integer.parseInt(param);
			index += 1;
		}

		final int stateChest  = 1;
		final int stateBiceps = 2;
		final int stateBack   = 3;
		int state = stateChest;

		int chest  = 0;
		int biceps = 0;
		int back   = 0;

		for (int i = 0; i < tasks.length; i++) {

			switch (state) {
				case stateChest:
					state = stateBiceps;

					chest += tasks[i];
					break;

				case stateBiceps:
					state = stateBack;

					biceps += tasks[i];
					break;

				case stateBack:
					state = stateChest;

					back += tasks[i];
					break;
			}
		}

		String answer = "no answer!";
		if (chest > biceps && chest > back) {
			answer = "chest";
		} else if (biceps > chest && biceps > back) {
			answer = "biceps";
		} else if (back > chest && back > biceps) {
			answer = "back";
		}
		System.out.println(answer);
	}
}