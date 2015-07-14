package boldinsv.problems.p544.A;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import boldinsv.problems.p544.A.C544A.Algorithm544A;
import boldinsv.problems.p544.B.C544B;

@RunWith(DataProviderRunner.class)
public class C544ATest {
	private String[] inputData = {"2", "aaacas"};
	private String[] outputData = {"YES", "aaa", "cas"};
	
	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm544A algorithm = (new C544A()).new Algorithm544A();
		algorithm.init();
		
		assertEquals(algorithm.k, 2);
		assertArrayEquals(algorithm.q, "aaacas".toCharArray());
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm544A algorithm = (new C544A()).new Algorithm544A();
		algorithm.init();
		algorithm.run();
		
		List<String> lines = new ArrayList<String>();
		
		lines.add("aaa");
		lines.add("ca");
		lines.add("s");
		
		assertEquals(algorithm.lines, lines);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm544A algorithm = (new C544A()).new Algorithm544A();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}
	
	@Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p544/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C544A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}
