package boldinsv.problems.p219.C;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import boldinsv.problems.p219.C.C219C.Algorithm219C;
import boldinsv.problems.p544.B.C544B;

@RunWith(DataProviderRunner.class)
public class C219CTest {
	private String[] inputData = {"6 3", "ABBACC"};
	private String[] outputData = {"2", "ABCACA"};

	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		Algorithm219C algorithm = (new C219C()).new Algorithm219C();
		algorithm.init();
		
		Queue<Character> line = new LinkedList<Character>();
		for (Character ch: "ABBACC".toCharArray()) {
			line.add(ch);
		}
		
		assertEquals(algorithm.n, 6);
		assertEquals(algorithm.k, 3);
		assertEquals(algorithm.line, line);
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		Algorithm219C algorithm = (new C219C()).new Algorithm219C();
		algorithm.init();
		algorithm.run();
		
		List<Character> answerLine = new ArrayList<Character>();
		for (Character ch: "ABCACA".toCharArray()) {
			answerLine.add(ch);
		}
		
		assertEquals(algorithm.number, 2);
		assertEquals(algorithm.answerLine, answerLine);
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		Algorithm219C algorithm = (new C219C()).new Algorithm219C();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}

	@Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p219/C/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C219C.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}
