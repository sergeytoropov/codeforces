package boldinsv.problems.p525.C;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import boldinsv.junit.Pipe;
import boldinsv.problems.p525.C.C525C.Algorithm525C;
import boldinsv.problems.p525.C.C525C.Run;

public class C525CTest {
	private String[] inputData = {"4", "2 4 4 2"};
	private String[] outputData = {"8"};

	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm525C algorithm = (new C525C()).new Algorithm525C();
		algorithm.init();
		
		assertEquals(algorithm.n, 4);
		assertArrayEquals(algorithm.li, new int[] {2, 4, 4, 2});
	}

	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm525C algorithm = (new C525C()).new Algorithm525C();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.sum, 8);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm525C algorithm = (new C525C()).new Algorithm525C();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
	
	@Test
	public void runRun() {
		Map<Integer, Integer> m = new TreeMap<Integer, Integer>();
		m.put(4, 2);
		m.put(2, 2);
		
		Run run = (new C525C()).new Run(new int[] {2, 4, 4, 2});
		assertEquals(run.m, m);
	}
	
	@Test
	public void prepareRun() {
		pipe.disable();
		
		Map<Integer, Integer> m;
		Run run;
		
		m = new TreeMap<Integer, Integer>();
		m.put(4, 2);
		m.put(2, 2);
		
		run = (new C525C()).new Run(new int[] {2, 4, 4, 2});
		run.prepare();
		assertEquals(run.m, m);
		
		run = (new C525C()).new Run(new int[] {2, 4, 4, 4, 2});
		run.prepare();
		assertEquals(run.m, m);
		
		run = (new C525C()).new Run(new int[] {2, 4, 2, 4, 2});
		run.prepare();
		assertEquals(run.m, m);
		
		m = new TreeMap<Integer, Integer>();
		m.put(4, 4);
		m.put(3, 2);
		
		run = (new C525C()).new Run(new int[] {4, 3, 3, 4, 4, 4, 4});
		run.prepare();
		assertEquals(run.m, m);
		
		m = new TreeMap<Integer, Integer>();
		m.put(4, 4);
		m.put(3, 4);
		m.put(2, 4);
		
		run = (new C525C()).new Run(new int[] {4, 4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2});
		run.prepare();
		assertEquals(run.m, m);
		
		m =  new TreeMap<Integer, Integer>();
		m.put(100006, 0);
		m.put(100005, 2);
		m.put(100004, 0);
		m.put(100003, 2);
		
		run = (new C525C()).new Run(new int[] {100003, 100004, 100005, 100006});
		run.prepare();
		assertEquals(run.m, m);
	}
	
	@Test
	public void summaryRun() {
		Run run;
		
		run = (new C525C()).new Run(new int[] {2, 4, 4, 2});
		run.prepare();
		run.summary();
		assertEquals(run.sum, 8);

		run = (new C525C()).new Run(new int[] {4, 3, 3, 4, 4, 4, 4});
		run.prepare();
		run.summary();
		assertEquals(run.sum, 16);
		
		run = (new C525C()).new Run(new int[] {4, 4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2});
		run.prepare();
		run.summary();
		assertEquals(run.sum, 29);
		
		run = (new C525C()).new Run(new int[] {100003, 100004, 100005, 100006});
		run.prepare();
		run.summary();
		assertEquals(run.sum, 10000800015L);
	}
	
	@Test
	@Ignore
	public void comparatorSet() {
		pipe.disable();
		
		class Item implements Comparator<Integer> {
			public int compare(Integer a , Integer b) {
				int result = 0;
				if (a > b) {
					result = -1;
				} else if (a < b) {
					result = 1;
				}
				return result;
			}
		}
		
		Item item = new Item();
		Set<Integer> set = new TreeSet<Integer>(item);
		for (int index = 0; index < 20; index++) {
			set.add(index);
		}
		
		for (Integer digit : set) {
			System.out.print(digit + ", ");
		}
		System.out.println();
	}
	
	@Test
	@Ignore
	public void comparatorMap() {
		pipe.disable();
		
		class Item implements Comparator<Integer> {
			public int compare(Integer a, Integer b) {
				int result = 0;
				if (a > b) {
					result = -1;
				} else if (a < b) {
					result = 1;
				}
				return result;
			}
		}
		
		Map<Integer, String> map = new TreeMap<Integer, String>(new Item());
		for (int index = 0; index < 20; index++) {
			map.put(index, "index = [" + index + "]");
		}
		
		for (Map.Entry<Integer, String> pair : map.entrySet()) {
			System.out.println("key = " + pair.getKey() + ", value = " + pair.getValue());
		}
	}
	
	@Test
	@Ignore
	public void set() {
		pipe.disable();
		
		Set<Integer> set = new HashSet<Integer>();
		for (int index = 0; index < Integer.MAX_VALUE % 1000; index++) {
			set.add(index);
		}
		System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE + ", set.size = " + set.size());
	
		Set<Integer> clone = new HashSet<Integer>();
		clone.addAll(set);
		for (Integer each : clone) {
			if (each % 100 != 0) {
				set.remove(each);
			}
		}
		
		for (Integer each : set) {
			System.out.print(each + ", ");
		}
		System.out.println();
	}
}