package boldinsv.problems.p570.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C570A {
	public static int n;
	public static int m;
	public static long [][] aij;
	public static int answer;
	
	public static void init() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			try {
				String[] items = reader.readLine().split(" ");
				n = Integer.parseInt(items[0]);
				m = Integer.parseInt(items[1]);
				
				aij = new long[m][n];
				for (int i = 0; i < aij.length; i++) {
					items = reader.readLine().split(" ");
					for (int j = 0; j < items.length; j++) {
						aij[i][j] = Long.parseLong(items[j]);
					}
				}
			}
			finally {
				reader.close();
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static int winner(long[] town) {
		int winner = 0;
		long max = 0;
		for (int i = 0; i < town.length; i++) {
			if (max < town[i]) {
				winner = i;
				max = town[i];
			}
		}
		return winner;
	}

	public static void run() {
		long[] winners = new long[aij[0].length];
		for (int i = 0; i < aij.length; i++) {
			int winner = C570A.winner(aij[i]);
			winners[winner] += 1; 
		}
		answer = C570A.winner(winners) + 1;
	}
	
	public static void print() {
		System.out.println("" + answer);
	}
	
	public static void main(String[] args) {
		C570A.init();
		C570A.run();
		C570A.print();
	}
}