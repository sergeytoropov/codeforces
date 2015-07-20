package boldinsv.problems.p220.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class C220B {
	public static class Pair {
		public int begin;
		public int end;
		
		public Pair(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + begin;
			result = prime * result + end;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (begin != other.begin)
				return false;
			if (end != other.end)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Pair [begin=" + begin + ", end=" + end + "]";
		}
	}

	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P220B extends Problem {
		public P220B() {
			algorithm = new Algorithm220B();
		}
	}
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public class Algorithm220B implements Algorithm {
		public int n;
		public int m;
		public List<Integer> ai;
		public List<Pair> lr;
		public List<Integer> answer;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String[] items;
			
			try {
				try {
					items = reader.readLine().split(" ");
					n = Integer.parseInt(items[0]);
					m = Integer.parseInt(items[1]);
				
					items = reader.readLine().split(" ");
					ai = new ArrayList<Integer>();
					for (String item: items) {
						ai.add(Integer.parseInt(item));
					}
					
					lr = new ArrayList<Pair>();
					for (int index = 0; index < m; index++) {
						items = reader.readLine().split(" ");
						lr.add(new Pair(Integer.parseInt(items[0]) - 1, Integer.parseInt(items[1]) - 1));
					}
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			answer = new ArrayList<Integer>();
			Map<Integer, Integer> table = new TreeMap<Integer, Integer>();
			
			for (Pair pair: lr) {
				table.clear();
				
				for (int index = pair.begin; index <= pair.end; index++) {
					Integer number = ai.get(index);
					
					if (table.containsKey(number)) {
						table.put(number, table.get(number) + 1);
					} else {
						table.put(number, 1);
					}
				}
				
				int counter = 0;
				for (Map.Entry<Integer, Integer> record: table.entrySet()) {
					if (record.getKey() == record.getValue()) {
						counter += 1;
					}
				}
				
				answer.add(counter);
			}
		}
		
		public void print() {
			for (Integer number: answer) {
				System.out.println("" + number);
			}
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C220B()).new P220B();
		problem.solve();
	}
}
