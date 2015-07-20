package boldinsv.problems.p219.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class C219C {
	private static char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P219C extends Problem {
		public P219C() {
			algorithm = new Algorithm219C();
		}
	}
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print(); 
	}
	
	public class Algorithm219C implements Algorithm {
		private char[] abc; 
		private final Character[] ab = {'A', 'B'};
		private int answerFirst;
		private int answerSecond;
		
		public int n;
		public int k;
		public Queue<Character> line;
		public int number;
		public List<Character> answerLine;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String[] items;
			
			try {
				try {
					items = reader.readLine().split(" ");
					n = Integer.parseInt(items[0]);
					k = Integer.parseInt(items[1]);
					
					abc = new char[k];
					for (int index = 0; index < abc.length; index++) {
						abc[index] = C219C.abc[index];
					}
					
					line = new LinkedList<Character>();
					for (Character ch: reader.readLine().toCharArray()) {
						line.add(ch);
					}
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public int replaceColor(List<Character> tempLine, Character nextColor) {
			Character color = null;
			int number = 0;
			
			for (Character ch: abc) {
				if (!ch.equals(tempLine.get(0)) && !ch.equals(nextColor)) {
					color = ch;
					break;
				}
			}
			
			for (int index = 1; index < tempLine.size(); index += 2) {
				tempLine.set(index, color);
				number += 1;
			}
			
			return number;
		}
		
		public int replaceColor(List<Character> tempLine) {
			return replaceColor(tempLine, tempLine.get(0));
		}
		
		public void runAll() {
			List<Character> tempLine = new ArrayList<Character>();
			Character prevChar = null;
		
			final int fNextColor = 1;
			final int fAddColor = 2;
			final int fNewSubLine = 3;
			final int fReplaceColor = 4;
			final int fReplaceColorAndExit = 5;
			int state = fNewSubLine;
		
			number = 0;
			answerLine = new ArrayList<Character>();
			
			exit:
			while (true) {
				switch (state) {
					case fNewSubLine:
						state = fNextColor;
					
						prevChar = line.remove();
						tempLine.clear();
						tempLine.add(prevChar);
						break;
						
					case fNextColor:
						state = fReplaceColor;
						
						try {
							if (prevChar.equals(line.element())) {
								state = fAddColor;
							}
						} catch (NoSuchElementException nsee) {
							state = fReplaceColorAndExit;
						}
						break;
						
					case fAddColor:
						state = fNextColor;
						
						tempLine.add(line.remove());
						break;
					
					case fReplaceColor:
						state = fNewSubLine;
						
						number += replaceColor(tempLine, line.element());
						answerLine.addAll(tempLine);
						break;
						
					case fReplaceColorAndExit:
						number += replaceColor(tempLine);
						answerLine.addAll(tempLine);
						break exit;
				}
			}
		}
		
		public void runSmall() {
			int first = 0;
			int second = 0;
		
			number = Integer.MAX_VALUE;
			answerLine = null;
			for (int i = 0; i < 2; i++) {
				switch (i) {
					case 0:
						first = 0;
						second = 1;
						break;
						
					case 1:
						first = 1;
						second = 0;
						break;
				}
				
				int index = 0;
				int min = 0;
				
				for (Character ch: line) {
					int position = second;
					
					if (index++ % 2 == 0) {
						position = first;
					}
					
					if (!ch.equals(ab[position])) {
						min++;
					}
				}
				
				if (number > min) {
					number = min;
					answerFirst = first;
					answerSecond = second;
				}
			}
		}
		
		public void run() {
			if (k > 2) {
				runAll();
			} else {
				runSmall();
			}
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			
			System.out.println("" + number);
			if (k > 2) {
				for (Character ch: answerLine) {
					sb.append(String.valueOf(ch));
				}
			} else {
				for (int index = 0; index < n; index++) {
					int position = answerSecond;
					
					if (index % 2 == 0) {
						position = answerFirst;
					}
					sb.append(ab[position]);
				}
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C219C()).new P219C();
		problem.solve();
	}

}
