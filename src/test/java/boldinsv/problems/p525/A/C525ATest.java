package boldinsv.problems.p525.A;

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
import boldinsv.problems.p525.A.C525A.Algorithm525A;
import boldinsv.problems.p535.A.C535A;

@RunWith(DataProviderRunner.class)
public class C525ATest {
	private String[] inputData = {"5", "xYyXzZaZ"};
	private String[] outputData = {"2"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm525A algorithm = (new C525A()).new Algorithm525A();
		algorithm.init();
		
		assertEquals(algorithm.n, 5);
		assertArrayEquals(algorithm.rooms, new char[][] {{'Y', 'X', 'Z', 'Z'}, {'x', 'y', 'z', 'a'}});
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm525A algorithm = (new C525A()).new Algorithm525A();
		algorithm.init();
		algorithm.run();
		
		assertEquals(algorithm.numberOfKeys, 2);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm525A algorithm = (new C525A()).new Algorithm525A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
	
    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p525/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C525A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}
