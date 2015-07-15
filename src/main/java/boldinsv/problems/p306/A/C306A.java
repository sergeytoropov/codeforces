package boldinsv.problems.p306.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C306A {
	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P306A extends Problem {
		public P306A() {
			algorithm = new Algorithm306A();
		}
	}
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public class Algorithm306A implements Algorithm {
		public int n;
		public int m;
		public int[] answer;
		
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
			answer = new int[m];
			
			for (int index = 0; index < answer.length; index++) {
				answer[index] = n / m;
			}
			
			for (int index = 0; index < n % m; index++) {
				answer[index] += 1;
			}
			
			Arrays.sort(answer);
		}
		
		public void print() {
			StringBuilder builder = new StringBuilder();
			
			for (int person: answer) {
				builder.append(person);
				builder.append(" ");
			}
			
			System.out.println(builder.toString().trim());
		}
	}

	public static void main(String[] args) {
		Problem problem = (new C306A()).new P306A();
		problem.solve();
	}

}
