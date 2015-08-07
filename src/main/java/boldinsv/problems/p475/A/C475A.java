package boldinsv.problems.p475.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C475A {
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
	
	public class P475A extends Problem {
		public P475A() {
			algorithm = new Algorithm475A();
		}
	}
	
	public class Algorithm475A implements Algorithm {
		public static final int fPassander = 1;
		public static final int fEmpty = 0;
		public static final int fRoad = - 1;
		
		public int[][] bus = {
				{fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty,},
				{fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty,},
				{fEmpty, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad,},
				{fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty,},
		};
		
		public int k;

		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					k = Integer.parseInt(reader.readLine());
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			stop:
			for (int row = 0; row < bus[0].length; row++) {
				for (int column = 0; column < bus.length; column++) {
					if (bus[column][row] == fRoad) {
						continue;
					}
					if (k-- > 0) {
						bus[column][row] = fPassander;
					} else {
						break stop;
					}
				}
			}
		}
		
		public void print() {
			final String tb = "+------------------------+";
			final String a = "|D|)";
			final String b = "|.|";
			final String c = "..|";
			final String d = "|.|)";
		
			int column = 0;
			System.out.println(tb);
			for (char state: new char[] {'a', 'b', 'c', 'd'}) {
				StringBuffer sb = new StringBuffer();
				sb.append("|");
				for (int item = 0; item < bus[0].length; item++) {
					switch (bus[column][item]) {
						case fPassander:
							sb.append("O");
							break;
						case fEmpty:
							sb.append("#");
							break;
						case fRoad:
							sb.append(".");
							break;
					}
					sb.append(".");
				}
				switch (state) {
					case 'a':
						sb.append(a);
						break;
					case 'b':
						sb.append(b);
						break;
					case 'c':
						sb.append(c);
						break;
					case 'd':
						sb.append(d);
						break;
				}
				System.out.println(sb.toString());

				column++;
			}
			System.out.println(tb);
		}
	}
	
	public static void main(String[] args) {
		P475A problem = (new C475A()).new P475A();
		problem.solve();
	}
}
