package boldinsv.problems.p525.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class C525C {
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
	
	public class P525C extends Problem {
		public P525C() {
			algorithm = new Algorithm525C();
		}
	}
	
	public class Algorithm525C implements Algorithm {
		public int n;
		public int[] li;
		public long sum;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					n = Integer.parseInt(reader.readLine());
					String[] items = reader.readLine().split(" ");
					li = new int[items.length];
					for (int index = 0; index < items.length; index++) {
						li[index] = Integer.parseInt(items[index]);
					}
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			Run run = new Run(li);
			run.prepare();
			run.summary();
			sum = run.sum;
		}
		
		public void print() {
			System.out.println(String.valueOf(sum));
		}
	}
	
	public class Run {
		private class Order implements Comparator<Integer> {
			public int compare(Integer a, Integer b) {
				int result = 0;
				if (a > b) {
					result = -1;
				} else if (a < b) {
					result = 1;
				}
				return result;
			}
		}
	
		public Map<Integer, Integer> m;
		long sum;
		
		public Run(int[] li) {
			m = new TreeMap<Integer, Integer>(new Order());
        
			for (int index = 0; index < li.length; index++) {
				int key = li[index];
                if (m.containsKey(key)) {
                	m.put(key, m.get(key) + 1);
                } else {
                    m.put(key, 1);
                }
			}	
		}
		
		public void prepare() {
			boolean isFirstElement = true;
			Integer prevKey = null;
			Integer key = null;
			Integer prevValue = null;
			Integer value = null;
			
			for (Integer currentKey : cloneKeys()) {
				if (isFirstElement) {
					isFirstElement = false;
					prevKey = currentKey;
					prevValue = m.get(currentKey);
				} else {
					key = currentKey;
					value = m.get(currentKey);
					
					if (prevValue % 2 != 0) {
						if (prevKey - 1 == key) {
							m.put(key, value + 1);
						}
						m.put(prevKey, prevValue - 1);
					}
					
					prevKey = key;
					prevValue = m.get(key);
				}
			}
			if (prevKey != null && prevValue != null && prevValue % 2 != 0) {
				m.put(prevKey, prevValue - 1);
			}	
		}
		
		private Set<Integer> cloneKeys() {
			Set<Integer> keys = new TreeSet<Integer>(new Order());
			keys.addAll(m.keySet());
			return keys;
		}
		
		public void summary() {
			final int fQ4 = 1;
			final int fQA2 = 2;
			final int fQB2 = 3;
			int state = fQ4;
			long a = 0;
			long b = 0;
			long q = 0;
			
			sum = 0;
			for (Integer key : m.keySet()) {
				int quantity = m.get(key);
				
				if (quantity <= 0) {
					continue;
				}
				
				boolean continued = true;
				while (continued) {
					switch (state) {
						case fQ4:
							state = fQA2;
							
							q = quantity / 4;
							if (q > 0) {
								a = key;
								sum += a * a * q;
								quantity = quantity % 4;
							}
							break;
							
						case fQA2:
							state = fQ4;
							
							q = quantity / 2;
							if (q > 0) {
								state = fQB2;
								
								a = key;
							}
							continued = false;
							break;
							
						case fQB2:
							state = fQ4;
						
							q = quantity / 2;
							if (q > 0) {
								b = key;
								sum += a * b;
								quantity -= 2;
							}
							break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C525C()).new P525C();
		problem.solve();
	}
}
