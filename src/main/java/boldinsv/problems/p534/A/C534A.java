package boldinsv.problems.p534.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class C534A {
	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P534A extends Problem {
		public P534A() {
			algorithm = new Algorithm534A();
		}
	}
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public class Algorithm534A implements Algorithm {
		public int n;
		public List<Integer> students;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					n = Integer.parseInt(reader.readLine());
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			students = new ArrayList<Integer>();
			
			if (n == 1 || n == 2) {
				students.add(1);
			} else if (n == 3) {
				students.add(1);
				students.add(3);
			} else if (n == 4) {
				students.add(3);
				students.add(1);
				students.add(4);
				students.add(2);
			} else {
				for (int index = 1; index <= n; index += 2) {
					students.add(index);
				}
				for (int index = 2; index <= n; index += 2) {
					students.add(index);
				}
			}
		}
		
		public void print() {
			System.out.println(students.size());
			
			StringBuilder sb = new StringBuilder();
			for (Integer student: students) {
				sb.append(student);
				sb.append(" ");
			}
			System.out.println(sb.toString().trim());
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C534A()).new P534A();
		problem.solve();
	}
}
