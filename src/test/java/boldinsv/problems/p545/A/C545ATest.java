package boldinsv.problems.p545.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p545.A.C545A.Algorithm545A;

public class C545ATest {
	private String[] inputData = {"3", "-1 0 0", "0 -1 1", "0 2 -1"};
	private String[] outputData = {"2", "1 3"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm545A algorithm = (new C545A()).new Algorithm545A();
		algorithm.init();
		
		Assert.assertEquals(algorithm.n, 3);
		Assert.assertArrayEquals(algorithm.matrix, new int[][] {{-1, 0, 0}, {0, -1, 1}, {0, 2, -1}});
	}

	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm545A algorithm = (new C545A()).new Algorithm545A();
		algorithm.init();
		algorithm.run();
		
		Assert.assertEquals(algorithm.quantity, 2);
		Assert.assertArrayEquals(algorithm.cars, new int[] {1, 3});
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm545A algorithm = (new C545A()).new Algorithm545A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		Assert.assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
