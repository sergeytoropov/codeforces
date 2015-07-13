package boldinsv.problems.p545.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class C545A {
	public abstract class Problem {
		protected Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}

	public class P545A extends Problem {
		public P545A() {
			algorithm = new Algorithm545A();
		}
	}
	
	interface Algorithm {
		void init();
		void run();
		void print();
	}
	
	public class Algorithm545A implements Algorithm {
		public int n;
		public int[][] matrix;
		public int quantity;
		public int[] cars;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				try {
					n = Integer.parseInt(reader.readLine());
					matrix = new int[n][n];
					
					for (int index = 0; index < n; index++) {
						String[] items = reader.readLine().split(" ");
						
						int position = 0;
						for (String item: items) {
							matrix[index][position++] = Integer.parseInt(item);
						}
					}
				} finally {
					reader.close();
				}
			} catch(IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			List<Integer> list = new ArrayList<Integer>();
			
			quantity = list.size();
			for (int i = 0; i < matrix.length; i++) {
				list.add(new Integer(i));
				for (int j = 0; j < matrix[i].length; j++) {
					int car = matrix[i][j];
					
					if (!(car == -1 || car == 0 || car == 2)) {
						list.remove(list.size() - 1);
						break;
					}
				}
			}
			quantity = list.size();
			
			cars = new int[list.size()];
			
			int index = 0;
			for (Integer number: list) {
				cars[index++] = number + 1;
			}
		}
		
		public void print() {
			System.out.println("" + quantity);
			
			String s = "";
			for (int car: cars) {
				s += car + " ";
			}
			System.out.println(s.trim());
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C545A()).new P545A();
		problem.solve();
	}

}
