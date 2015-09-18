package boldinsv.problems.p570.A;

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
import boldinsv.problems.p544.B.C544B;

@RunWith(DataProviderRunner.class)
public class C570ATest {
	private String[] inputData = {"3 3", "1 2 3", "2 3 1", "1 2 1"};
	private String[] outputData = {"2"};

	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		C570A.init();
		
		assertEquals(3, C570A.n);
		assertEquals(3, C570A.m);
		assertArrayEquals(new long[][] {{1, 2, 3}, {2, 3, 1}, {1, 2, 1}}, C570A.aij);
	}

	@Test
	public void winner() {
		assertEquals(1, C570A.winner(new long[] {2, 3, 1}));
		assertEquals(0, C570A.winner(new long[] {3, 3, 3}));
		assertEquals(2, C570A.winner(new long[] {1, 2, 3}));
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		C570A.init();
		C570A.run();
		
		assertEquals(2, C570A.answer);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		C570A.init();
		C570A.run();
		C570A.print();
		
		assertArrayEquals(outputData, pipe.getAnswerDataStringArray());
	}

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p570/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C570A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}
