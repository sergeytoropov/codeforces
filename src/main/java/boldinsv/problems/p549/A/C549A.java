package boldinsv.problems.p549.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class C549A {
	public abstract class Problem {
		protected Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P549A extends Problem {
		public P549A() {
			algorithm = new Algorithm549A();
		}
	}
	
	interface Algorithm {
		void init();
		void run();
		void print();
	}
	
	public class Algorithm549A implements Algorithm {
		public int n;
		public int m;
		public char[][] matrix;
		public int answer;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String[] items;
			try {
				items = reader.readLine().split(" ");
			
				n = Integer.parseInt(items[0]);
				m = Integer.parseInt(items[1]);
			
				matrix = new char[n][m];
				for (int index = 0; index < n; index++) {
					char[] chars = reader.readLine().toCharArray();
				
					for (int j = 0; j < chars.length; j++) {
						matrix[index][j] = chars[j];
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
		public void run() {
			answer = 0;
			
			Set<Character> set = new HashSet();
			for (int i = 0; i < matrix.length - 1; i++) {
				for (int j = 0; j < matrix[i].length - 1; j++) {
					set.clear();
					
					set.add(matrix[i][j]);
					set.add(matrix[i][j + 1]);
					set.add(matrix[i + 1][j]);
					set.add(matrix[i + 1][j + 1]);
					
					if (set.contains(new Character('f')) && set.contains(new Character('a')) && set.contains(new Character('c')) && set.contains(new Character('e'))) {
						answer += 1;
					}
				}
			}
		}
		
		public void print() {
			System.out.println("" + answer);
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C549A()).new P549A();
		problem.solve();
	}
}
