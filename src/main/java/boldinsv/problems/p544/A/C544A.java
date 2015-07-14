package boldinsv.problems.p544.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C544A {
//abc: Aa Bb Cc Dd Ee Ff Gg Hh Ii Jj Kk Ll Mm Nn Oo Pp Qq Rr Ss Tt Uu Vv Ww Xx Yy Zz
	
	public abstract class Problem {
		protected Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P544A extends Problem {
		public P544A() {
			algorithm = new Algorithm544A();
		}
	}
	
	public interface Algorithm {
		void init();
		void run();
		void print();
	}
	
	public class Algorithm544A implements Algorithm {
		private char[] abc = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		private Set<Character> abcSet;
		
		public int k;
		public char[] q;
		public List<String> lines;
		
		public Algorithm544A() {
			abcSet = new HashSet<Character>();
			
			for (char ch: abc) {
				abcSet.add(new Character(ch));
			}
		}
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					k = Integer.parseInt(reader.readLine());
					q = reader.readLine().toCharArray();
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			final int EXIT      = 0;
			final int NEXT_CHAR = 1;
			final int NEW_LINE  = 2;
			final int ADD_CHAR  = 3;
			final int MAKE_LINE = 4;
			final int SAVE_LINE = 5;
			int state = NEXT_CHAR;
			int index = 0;
			char ch = 0;
			String tmp = "";
			
			lines = new ArrayList<String>();
			
			exit:
			while (true) {
				switch (state) {
					case NEXT_CHAR:
						state = NEW_LINE;
						if (index >= q.length) {
							state = EXIT;
						} else {
							ch = q[index++];
						}
						break;
						
					case NEW_LINE:
						state = ADD_CHAR;
						if (abcSet.contains(ch)) {
							state = SAVE_LINE;
							abcSet.remove(ch);
						}
						break;
						
					case ADD_CHAR:
						state = NEXT_CHAR;
						tmp += String.valueOf(ch);
						break;
						
					case MAKE_LINE:
						state = NEXT_CHAR;
						tmp = String.valueOf(ch);
						break;
						
					case SAVE_LINE:
						state = MAKE_LINE;
						if (!"".equals(tmp)) {
							lines.add(tmp);
						}
						break;
						
					case EXIT:
						lines.add(tmp);
						break exit;
				}
			}
		}
		
		public void print() {
			if (k > lines.size()) {
				System.out.println("NO");
			} else {
				int index = 1;
				
				System.out.println("YES");
				for (String line: lines) {
					if (index++ < k) {
						System.out.println(line);
					} else {
						System.out.print(line);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C544A()).new P544A();
		problem.solve();
	}

}