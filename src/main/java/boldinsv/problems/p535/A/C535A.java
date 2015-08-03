package boldinsv.problems.p535.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class C535A {
	public final static String[][] digitsConstants = {
		{"0", "zero"},
		{"1", "one"},
		{"2", "two"},
		{"3", "three"},
		{"4", "four"},
		{"5", "five"},
		{"6", "six"},
		{"7", "seven"},
		{"8", "eight"},
		{"9", "nine"},
		{"10", "ten"},
		{"11", "eleven"},
		{"12", "twelve"},
		{"13", "thirteen"},
		{"14", "fourteen"},
		{"15", "fifteen"},
		{"16", "sixteen"},
		{"17", "seventeen"},
		{"18", "eighteen"},
		{"19", "nineteen"},
		{"20", "twenty"},
		{"30", "thirty"},
		{"40", "forty"},
		{"50", "fifty"},
		{"60", "sixty"},
		{"70", "seventy"},
		{"80", "eighty"},
		{"90", "ninety"},
	};
	
	public static Map<Integer, String> digits;
	
	static {
		digits = new HashMap<Integer, String>();
		for (String[] desc: digitsConstants) {
			digits.put(Integer.parseInt(desc[0]), desc[1]);
		}
	}
	
	public abstract class Problem {
		protected Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P535A extends Problem {
		public P535A() {
			algorithm = new Algorithm535A();
		}
	}
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public class Algorithm535A implements Algorithm {
		public int s;
		public String answer;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					s = Integer.parseInt(reader.readLine());
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
			
		}
		
		public void run() {
			final int delta = 10;
			
			answer = null;
			if (s < 20) {
				answer = digits.get(s);
			} else {
				exit:
				for (int index = 20; index < 100; index += delta) {
					if (s >= index && s < index + delta) {
						answer = digits.get(index);
						if (s % index != 0) {
							answer +="-" + digits.get(s % index);
						}
						break exit;
					}
				}
			}
		}
		
		public void print() {
			System.out.println(answer);
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C535A()).new P535A();
		problem.solve();
	}
}
