package boldinsv.problems.p88;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C88A {
	private static String[] sort(String[] params, int pos) {
		
		String[] accord = {"", "", ""};

		if (pos == 1) {
			accord[0] = params[0];
			accord[1] = params[1];
			accord[2] = params[2];
		} else if (pos == 2) {
			accord[0] = params[0];
			accord[1] = params[2];
			accord[2] = params[1];
		} else if (pos == 3) {
			accord[0] = params[1];
			accord[1] = params[0];
			accord[2] = params[2];
		} else if (pos == 4) {
			accord[0] = params[1];
			accord[1] = params[2];
			accord[2] = params[0];
		} else if (pos == 5) {
			accord[0] = params[2];
			accord[1] = params[0];
			accord[2] = params[1];
		} else if (pos == 6) {
			accord[0] = params[2];
			accord[1] = params[1];
			accord[2] = params[0];
		}
		return accord;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] params = reader.readLine().split(" ");
		reader.close();

		String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "B", "H"};

		String answer = "";
		for (int i = 1; i < 7; i++) {
			String[] accord = sort(params, i);
	
			final int stateInit = 0;
			final int stateExit = 1;
			final int stateA = 2;
			final int stateB = 3;
			int state = stateInit;

			int index = 0;
			int position = 0;
			int a = 0;
			int b = 0;

			boolean isStoped = false;
			while (!isStoped) {

				switch (state) {
					case stateInit:
						if (accord[position].equals(notes[index % 12])) {
							position += 1;
							state = stateA;
						}
						break;

					case stateExit:
						isStoped = true;
					   	break;

					case stateA:
						a += 1;
						if (accord[position].equals(notes[index % 12])) {
							position += 1;
							state = stateB;
						}
						break;

					case stateB:
						b += 1;
						if (accord[position].equals(notes[index % 12])) {
							state = stateExit;
						}
						break;
				}
				index += 1;
			}

			answer = "strange";
			if (a == 4 && b == 3) {
				answer = "major";
				break;
			} else if (a == 3 && b == 4) {
				answer = "minor";
				break;
			}
		}
		System.out.println(answer);		
	}
}