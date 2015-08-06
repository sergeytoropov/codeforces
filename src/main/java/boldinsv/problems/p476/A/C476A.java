package boldinsv.problems.p476.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C476A {
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
	
	public class P476A extends Problem {
		public P476A() {
			algorithm = new Algorithm476A();
		}
	}
	
	public class Algorithm476A implements Algorithm {
		public int n;
		public int m;
		public int answer;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					String[] items = reader.readLine().split(" ");
					n = Integer.parseInt(items[0]);
					m = Integer.parseInt(items[1]);
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			int steps = n % 2 == 0 ? n / 2 : (n - 1) / 2 + 1;
			answer = -1;
			while (steps <= n) {
				if (steps % m == 0) {
					answer = steps;
					break;
				}
				steps++;
			}
		}
		
		public void print() {
			System.out.println(String.valueOf(answer));
		}
	}
	public static void main(String[] args) {
		Problem problem = (new C476A()).new P476A();
		problem.solve();
	}
}
