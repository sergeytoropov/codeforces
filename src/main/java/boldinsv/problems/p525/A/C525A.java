package boldinsv.problems.p525.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class C525A {
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
	
	public class P525A extends Problem {
		public P525A() {
			algorithm = new Algorithm525A();
		}
	}
	
	public class Algorithm525A implements Algorithm {
		private final int fDoor = 0;
		private final int fKey = 1;
		
		public int n;
		public char[][] rooms;
		public int numberOfKeys;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					n = Integer.parseInt(reader.readLine());
					rooms = new char[2][n - 1];
					int index = 0;
					int state = fKey;
					for (char item: reader.readLine().toCharArray()) {
						switch (state) {
							case fDoor:
								state = fKey;
								rooms[fDoor][index++] = item;
								break;
							case fKey:
								state = fDoor;
								rooms[fKey][index] = item;
								break;
						}
					}
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			Map<Character, Integer> keys = new HashMap<Character, Integer>();
			
			numberOfKeys = 0;
			for (int index = 0; index < rooms[fDoor].length; index++) {
				Character key = rooms[fKey][index];
			
				if (keys.containsKey(key)) {
					keys.put(key, keys.get(key) + 1);
				} else {
					keys.put(key, 1);
				}
				
				Character door = rooms[fDoor][index];
				
				door = Character.toLowerCase(door);
				if (keys.containsKey(door)) {
					if (keys.get(door) > 0) {
						keys.put(door, keys.get(door) - 1);
					} else {
						numberOfKeys++;
					}
				} else {
					numberOfKeys++;
				}
			}
		}
		
		public void print() {
			System.out.println("" + numberOfKeys);
		}
	}

	public static void main(String[] args) {
		Problem problem = (new C525A()).new P525A();
		problem.solve();
	}
}
