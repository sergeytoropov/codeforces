package boldinsv.problems.p472.A;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p472.A.C472A.Algorithm472A;

public class C472ATest {
	private String[] inputData = {"12"};
	private String[] outputData = {"4 8"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
	
		Algorithm472A algorithm = (new C472A()).new Algorithm472A();
		algorithm.init();
		
		assertEquals(algorithm.n, 12);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm472A algorithm = (new C472A()).new Algorithm472A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.x, 4);
		assertEquals(algorithm.y, 8);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm472A algorithm = (new C472A()).new Algorithm472A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
	
	@Test
	public void primesNumber() {
		assertArrayEquals(C472A.primesNumber(20), new int [] {0, 1, 2, 3, 5, 7, 11, 13, 17, 19});
	}
	
	@Test
	@Ignore
	public void bitSet() {
		pipe.disable();
		
		System.out.println("Start test BitSet");
		int top = 0;
		BitSet bits = new BitSet(top);
		for (int index = 0; index <= top; index++) {
			System.out.println(bits.get(index));
			bits.set(index);
			System.out.println(bits.get(index));
		}
	}
	
	@Test
	public void binarySearch() {
		pipe.disable();

		int top = 20;
		int[] primes = C472A.primesNumber(top);
		for (int index = 0; index <= top; index++) {
			System.out.println("index = " + index + " - " + Arrays.binarySearch(primes, index));
		}
	}
}
