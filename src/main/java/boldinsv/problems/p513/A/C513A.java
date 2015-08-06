package boldinsv.problems.p513.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C513A {
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
	
	public class P513A extends Problem {
		public P513A() {
			algorithm = new Algorithm513A();
		}
	}
	
	public class Algorithm513A implements Algorithm {
		public int n1;
		public int n2;
		public int k1;
		public int k2;
		public String answer;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					String[] items = reader.readLine().split(" ");
					n1 = Integer.parseInt(items[0]);
					n2 = Integer.parseInt(items[1]);
					k1 = Integer.parseInt(items[2]);
					k2 = Integer.parseInt(items[3]);
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			answer = "Second"; // n1 <= n2
			if (n1 > n2) {
				answer = "First";
			}
		}
		
		public void print() {
			System.out.println(answer);
		}
	}
	
	public static void main(String[] args) {
		P513A problem = (new C513A()).new P513A();
		problem.solve();
	}
}
