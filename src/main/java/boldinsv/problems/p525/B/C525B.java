package boldinsv.problems.p525.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C525B {
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P525B extends Problem {
		public P525B() {
			//algorithm = new SlowlyAlgorithm525B();
			//algorithm = new FasterAlgorithm525B();
			algorithm = new SuperFasterAlgorithm525B();
		}
	}
	
	public abstract class AbstractAlgorithm525B implements Algorithm {
		public char[] s;
		public int m;
		public int[] ai;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					s = reader.readLine().toCharArray();
					m = Integer.parseInt(reader.readLine());
					String[] items = reader.readLine().split(" ");
					ai = new int[items.length];
					int index = 0;
					for (String item: items) {
						ai[index++] = Integer.parseInt(item);
					} 
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public abstract void run();
		
		public void print() {
			System.out.println(String.valueOf(s));
		}
	}

	// Данных алгоритм не проходит тесты по времени
	public class SlowlyAlgorithm525B extends AbstractAlgorithm525B {
		public void run() {
			for (int day = 0; day < ai.length; day++) {
				for (int index = ai[day] - 1; index < s.length / 2; index++) {
					int position = s.length - index - 1;
					char tmp = s[index];
					s[index] = s[position];
					s[position] = tmp;
				}
			}
		}
	}
	
	// Данных алгоритм не проходит тесты по времени
	public class FasterAlgorithm525B extends AbstractAlgorithm525B {
		public void run() {
			int[] map = new int[s.length / 2];
			int start = map.length;
			for (int day = 0; day < ai.length; day++) {
				int position = ai[day] - 1;
				if (start > position) {
					start = position;
				}
				for (int index = position; index < map.length; index++) {
					map[index]++;
				}
			}
			for (int index = start; index < map.length; index++) {
				if (map[index] % 2 != 0) {
					int position = s.length - index - 1;
					char tmp = s[index];
					s[index] = s[position];
					s[position] = tmp;
				}
			}
		}
	}

	public class SuperFasterAlgorithm525B extends AbstractAlgorithm525B {
		public void run() {
			int[] map = new int[s.length / 2];
			int start = map.length;
			for (int day = 0; day < ai.length; day++) {
				int position = ai[day] - 1;
				if (start > position) {
					start = position;
				}
				map[position]++;
			}
			boolean swapped = false;
			for (int index = start; index < map.length; index++) {
				swapped = (!swapped & map[index] % 2 != 0) || (swapped & map[index] % 2 == 0);
				if (swapped) {
					int position = s.length - index - 1;
					char tmp = s[index];
					s[index] = s[position];
					s[position] = tmp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C525B()).new P525B();
		problem.solve();
	}
}
