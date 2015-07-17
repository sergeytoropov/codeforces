package boldinsv.problems.p219.A;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p219.A.C219A.StandartAlgorithm219A;

public class C219ATest {
	private String[] inputData = {"2", "aazz"};
	private String[] outputData = {"azaz"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		StandartAlgorithm219A algorithm = (new C219A()).new StandartAlgorithm219A();
		algorithm.init();
		
		Map<Character, Integer> abcMap = new HashMap<Character, Integer>();
		for (char ch: C219A.abc) {
			abcMap.put(Character.valueOf(ch), new Integer(0));
		}
		abcMap.put(Character.valueOf('a'), 2);
		abcMap.put(Character.valueOf('z'), 2);

		assertEquals(algorithm.k, 2);
		assertEquals(algorithm.abcMap, abcMap);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		StandartAlgorithm219A algorithm = (new C219A()).new StandartAlgorithm219A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.isKString, true);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		StandartAlgorithm219A algorithm = (new C219A()).new StandartAlgorithm219A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
