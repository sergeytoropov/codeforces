package boldinsv.problems.p513.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p513.A.C513A.Algorithm513A;

public class C513ATest {
	private String[] inputData = {"2 2 1 2"};
	private String[] outputData = {"Second"};
	
	private String[] inputData2 = {"2 1 1 1"};
	private String[] outputData2 = {"First"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm513A algorithm = (new C513A()).new Algorithm513A();
		algorithm.init();
		
		assertEquals(algorithm.n1, 2);
		assertEquals(algorithm.n2, 2);
		assertEquals(algorithm.k1, 1);
		assertEquals(algorithm.k2, 2);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm513A algorithm = (new C513A()).new Algorithm513A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.answer, "Second");
	}

	@Test
	public void run2() {
		pipe.prepare(inputData2);
		
		Algorithm513A algorithm = (new C513A()).new Algorithm513A();
		algorithm.init();
		algorithm.run();
	
		assertEquals(algorithm.answer, "First");
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm513A algorithm = (new C513A()).new Algorithm513A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
