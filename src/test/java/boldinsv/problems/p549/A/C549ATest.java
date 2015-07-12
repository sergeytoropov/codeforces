package boldinsv.problems.p549.A;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import boldinsv.problems.p549.A.C549A.*;

@RunWith(DataProviderRunner.class)
public class C549ATest {
	private String[] inputData = {"4 4", "xxxx", "xfax", "xcex", "xxxx"};
	private String[] outputData = {"1"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm549A algorithm = (new C549A()).new Algorithm549A();
		algorithm.init();
		
		assertEquals(algorithm.n, 4);
		assertEquals(algorithm.m, 4);
		assertArrayEquals(algorithm.matrix, new char[][] {{'x', 'x', 'x', 'x'}, {'x', 'f', 'a', 'x'}, {'x', 'c', 'e', 'x'}, {'x', 'x', 'x', 'x'}});
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm549A algorithm = (new C549A()).new Algorithm549A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.answer, 1);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm549A algorithm = (new C549A()).new Algorithm549A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
	
    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p549/A/answers")
	public void main(Answer answer) throws IOException {
		pipe.prepare(answer.getInputData());
		
		C549A.main(null);
		
		assertEquals(pipe.getAnswerData(), answer.getActualData());
	}

}
