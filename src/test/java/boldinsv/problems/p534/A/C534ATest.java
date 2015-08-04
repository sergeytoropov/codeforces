package boldinsv.problems.p534.A;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p534.A.C534A.Algorithm534A;

public class C534ATest {
	private String[] inputData = {"5"};
	private String[] outputData = {"5", "1 3 5 2 4"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm534A algorithm = (new C534A()).new Algorithm534A();
		algorithm.init();
		
		assertEquals(algorithm.n, 5);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm534A algorithm = (new C534A()).new Algorithm534A();
		algorithm.init();
		algorithm.run();
		
		List<Integer> students = new ArrayList<Integer>();
		for (int student: new int[] {1, 3, 5, 2, 4}) {
			students.add(student);
		}
		
		assertEquals(algorithm.students.size(), 5);
		assertEquals(algorithm.students, students);
	}

	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm534A algorithm = (new C534A()).new Algorithm534A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
