package boldinsv.problems.p4.C;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import boldinsv.problems.p4.C.C4C.Algorithm4C;
import boldinsv.problems.p4.C.C4C.User;
import boldinsv.problems.p4.C.C4C;

@RunWith(DataProviderRunner.class)
public class C4CTest {
	private String[] inputData = {"6", "first", "first", "second", "second", "third", "third"};
	private String[] outputData = {"OK", "first1", "OK", "second1", "OK", "third1"};

	@Rule
	public Pipe pipe = new Pipe();

	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm4C algorithm = (new C4C()).new Algorithm4C();
		algorithm.init();
		
		assertEquals(algorithm.n, 6);
		
		List<User> users = new ArrayList<User>();
		for (String username : new String[] {"first", "first", "second", "second", "third", "third"}) {
			users.add(new User(username));
		}
		assertEquals(algorithm.users, users);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm4C algorithm = (new C4C()).new Algorithm4C();
		algorithm.init();
		algorithm.run();
		
		List<String> answer = new ArrayList<String>();
		for (String name : outputData) {
			answer.add(name);
		}
		assertEquals(algorithm.answer, answer);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm4C algorithm = (new C4C()).new Algorithm4C();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p4/C/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C4C.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }	
	
	@Test
	public void user() {
		User uAa = new User("aa");
		User uAbc = new User("abc");
		
		assertTrue(uAa.equals(uAa));
		assertTrue(uAa.fHash == uAa.fHash);
		assertFalse(uAa.equals(uAbc));
		assertFalse(uAa == uAbc);
	}
	
	@Test
	public void hash() {
		final long fP = C4C.P;
		assertEquals(C4C.hash(""), 0);
		assertEquals(C4C.hash("a"), 1);
		assertEquals(C4C.hash("aa"), 1 + fP);
		assertEquals(C4C.hash("aaa"), 1 + fP + fP * fP);
		assertEquals(C4C.hash("b"), 2);
		assertEquals(C4C.hash("bb"), 2 + 2 * fP);
		assertEquals(C4C.hash("abc"), 1 + 2 * fP + 3 * fP * fP);
	}

	@Test
	public void hashMaxString() {
		C4C.hash("jzsyjnxttliyfpunxyhsouhunenzxedi");
	}
	
	@Test
	@Ignore
	public void primes() {
		List<Integer> primes = new ArrayList<Integer>();
		
		for (int digit = 1; digit < 100; digit++) {
			boolean isPrime = true;
			
			for (int index = 2; index < digit; index++) {
				if (digit % index == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes.add(digit);
			}
		}
		
		for (Integer each : primes) {
			System.out.print(each + ", ");
		}
		System.out.println();
	}
	
	@Test
	@Ignore
	public void overflowLong() {
		final int p = 31;
		for (int index = 0; index < 32; index++) {
			long result = p;
			
			for (int pow = 0; pow < index; pow++) {
				result *= p;
			}
			System.out.println("pow = " + (index + 1) + ", value = " + result + ", max long =" + Long.MAX_VALUE);
		}
	}
}

//jzsyjnxttliyfpunxyhsouhunenzxedi

