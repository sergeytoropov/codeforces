package boldinsv.problems.p527.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C527A {
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
	
	public class P527A extends Problem {
		public P527A() {
			algorithm = new Algorithm527A();
		}
	}
	
	public class Algorithm527A implements Algorithm {
		public long a;
		public long b;
		public long answer;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					String[] items = reader.readLine().split(" ");
					a = Long.parseLong(items[0]);
					b = Long.parseLong(items[1]);
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			answer = 0;
			
			exit:
			while (true) {
				long delta = a % b;
				answer += (a - delta) / b;
				a = b;
				b = delta;
				if (delta == 0) {
					break exit;
				}
			}
		}
		
		public void print() {
			System.out.println(String.valueOf(answer));
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C527A()).new P527A();
		problem.solve();
	}
}
