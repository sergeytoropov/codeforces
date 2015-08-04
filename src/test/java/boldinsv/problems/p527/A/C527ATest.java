package boldinsv.problems.p527.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p527.A.C527A.Algorithm527A;

public class C527ATest {
	private String[] inputData = {"10 7"};
	private String[] outputData = {"6"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm527A algorithm = (new C527A()).new Algorithm527A();
		algorithm.init();
		
		assertEquals(algorithm.a, 10);
		assertEquals(algorithm.b, 7);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm527A algorithm = (new C527A()).new Algorithm527A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.answer, 6L);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm527A algorithm = (new C527A()).new Algorithm527A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
