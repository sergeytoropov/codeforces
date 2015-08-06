package boldinsv.problems.p476.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p476.A.C476A.Algorithm476A;

public class C476ATest {
	private String[] inputData = {"3 5"};
	private String[] outputData = {"-1"};

	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm476A algorithm = (new C476A()).new Algorithm476A();
		algorithm.init();
		
		assertEquals(algorithm.n, 3);
		assertEquals(algorithm.m, 5);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm476A algorithm = (new C476A()).new Algorithm476A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.answer, -1);
	}

	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm476A algorithm = (new C476A()).new Algorithm476A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
