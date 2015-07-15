package boldinsv.problems.p306.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p306.A.C306A.Algorithm306A;

public class C306ATest {
	private String[] inputData = {"18 7"};
	private String[] outputData = {"2 2 2 3 3 3 3"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm306A algorithm = (new C306A()).new Algorithm306A();
		algorithm.init();
		
		assertEquals(algorithm.n, 18);
		assertEquals(algorithm.m, 7);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm306A algorithm = (new C306A()).new Algorithm306A();
		algorithm.init();
		algorithm.run();
		
		assertArrayEquals(algorithm.answer, new int[] {2, 2, 2, 3, 3, 3, 3});
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm306A algorithm = (new C306A()).new Algorithm306A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
	
	@Test
	public void main() {
		//C306A.main(null);
	}

}
