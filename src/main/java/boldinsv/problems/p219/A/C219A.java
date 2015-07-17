package boldinsv.problems.p219.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class C219A {
	public static char[] abc = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P219A extends Problem {
		public P219A() {
			algorithm = new StandartAlgorithm219A();
		}
	}
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public abstract class AbstractAlgorithm219A implements Algorithm {
		public Map<Character, Integer> abcMap;
		public int k;
		public boolean isKString;
		
		public AbstractAlgorithm219A() {
			abcMap = new HashMap<Character, Integer>();
			for (char ch: C219A.abc) {
				abcMap.put(Character.valueOf(ch), new Integer(0));
			}
		}
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					k = Integer.parseInt(reader.readLine());
					
					for (char ch: reader.readLine().toCharArray()) {
						abcMap.put(Character.valueOf(ch), abcMap.get(Character.valueOf(ch)) + 1);
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
			StringBuilder sb = new StringBuilder();
			
			if (isKString) {
				for (Map.Entry<Character, Integer> item: abcMap.entrySet()) {
					for (int index = 0; index < item.getValue() / k; index++) {
						sb.append(item.getKey());
					}
				}
				
				String smallString = sb.toString();
				sb = new StringBuilder();
				for (int index = 0; index < k; index++) {
					sb.append(smallString);
				}
				
				System.out.println(sb.toString());
			} else {
				System.out.println("-1");
			}
		}
	}
	
	public class StandartAlgorithm219A extends AbstractAlgorithm219A {
		public void run() {
			isKString = true;
			for (Map.Entry<Character, Integer> item: abcMap.entrySet()) {
				Integer value = item.getValue();
			
				if (value > 0 && value % k != 0) {
					isKString = false;
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C219A()).new P219A();
		problem.solve();
	}

}
