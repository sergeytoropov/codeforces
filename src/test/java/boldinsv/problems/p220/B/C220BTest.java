package boldinsv.problems.p220.B;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p220.B.C220B.*;

public class C220BTest {
	private String[] inputData = {"7 2", "3 1 2 2 3 3 7", "1 7", "3 4"};
	private String[] outputData = {"3", "1"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm220B algorithm = (new C220B()).new Algorithm220B();
		algorithm.init();
	
		List<Integer> ai = new ArrayList<Integer>();
		ai.add(3);
		ai.add(1);
		ai.add(2);
		ai.add(2);
		ai.add(3);
		ai.add(3);
		ai.add(7);
		
		List<Pair> lr = new ArrayList<Pair>();
		lr.add(new Pair(0, 6));
		lr.add(new Pair(2, 3));
		
		assertEquals(algorithm.n, 7);
		assertEquals(algorithm.m, 2);
		assertEquals(algorithm.ai, ai);
		assertEquals(algorithm.lr, lr);
	}

	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm220B algorithm = (new C220B()).new Algorithm220B();
		algorithm.init();
		algorithm.run();
		
		List<Integer> answer = new ArrayList<Integer>();
		answer.add(3);
		answer.add(1);
		
		assertEquals(algorithm.answer, answer);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm220B algorithm = (new C220B()).new Algorithm220B();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}