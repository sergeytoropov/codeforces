package boldinsv.problems.p475.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p475.A.C475A.Algorithm475A;

public class C475ATest {
	private String[] inputData = {"9"};
	private String[] outputData = {
            "+------------------------+",
            "|O.O.O.#.#.#.#.#.#.#.#.|D|)",
            "|O.O.O.#.#.#.#.#.#.#.#.|.|",
            "|O.......................|",
            "|O.O.#.#.#.#.#.#.#.#.#.|.|)",
            "+------------------------+",
	};

	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm475A algorithm = (new C475A()).new Algorithm475A();
		algorithm.init();
		
		assertEquals(algorithm.k,  9);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm475A algorithm = (new C475A()).new Algorithm475A();
		algorithm.init();
		algorithm.run();
		
		final int fPassanger = Algorithm475A.fPassander;
		final int fEmpty = Algorithm475A.fEmpty;
		final int fRoad = Algorithm475A.fRoad;
		
		int[][] bus = {
				{fPassanger, fPassanger, fPassanger, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty,},
				{fPassanger, fPassanger, fPassanger, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty,},
				{fPassanger, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad, fRoad,},
				{fPassanger, fPassanger, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty, fEmpty,},
		};
		
		assertArrayEquals(algorithm.bus, bus);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm475A algorithm = (new C475A()).new Algorithm475A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
}
