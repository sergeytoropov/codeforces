package boldinsv.problems.p472.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class C472A {
	/*
	 * Числа, которые имеют более двух делителей, называются составными. 
	 * Числа, которые имеют только два делителя: единица и само это число, называются простыми числами.
	 * Число 1 имеет только один делить, а именно само это число. Единица не относится ни к простым, ни к составным числам.
	 */
	
	public static int[] primesNumber() {
		return primesNumber(1000000);
	}
	
	/*
	 * Алгоритм "Решето Эратосфена" - нахождение простых чисел.
	 */
	public static int[] primesNumber(int top) {
		BitSet bits = new BitSet(top + 1);
		int index;
		
		index = 0;
		while (index <= top) {
			bits.set(index++);
		}
	
		index = 2;
		while (index * index <= top) {
			if (bits.get(index)) {
				int number = 2 * index;
				while (number <= top) {
					bits.clear(number);
					number += index;
				}
			}
			index++;
		}

		int length = 0;
		index = 0;
		while (index <= top) {
			if (bits.get(index++)) {
				length++;
			}
		}
		
		int[] primesNumberArray = new int[length];
		int position = 0;
		index = 0;
		while (index <= top) {
			if (bits.get(index)) {
				primesNumberArray[position++] = index;
			}
			index++;
		}
		return primesNumberArray;	
	}
	
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
	
	public class P472A extends Problem {
		public P472A() {
			algorithm = new Algorithm472A();
		}
	}
	
	public class Algorithm472A implements Algorithm {
		public int n;
		public int x;
		public int y;
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				try {
					n = Integer.parseInt(reader.readLine());
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			int[] primes = C472A.primesNumber();
			for (x = 0; x <= n; x++) {
				y = n - x;
				if (Arrays.binarySearch(primes, x) < 0 && Arrays.binarySearch(primes, y) < 0) {
					break;
				}
			}
		}
		
		public void print() {
			System.out.println(x + " " + y);
		}
	}
	
	public static void main(String[] args) {
		P472A problem = (new C472A()).new P472A();
		problem.solve();
	}
}
