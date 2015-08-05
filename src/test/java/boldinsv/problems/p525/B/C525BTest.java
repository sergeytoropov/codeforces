package boldinsv.problems.p525.B;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

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
import boldinsv.problems.p525.B.C525B.*;
import boldinsv.problems.p535.A.C535A;

@RunWith(DataProviderRunner.class)
public class C525BTest {
	private String[] inputData = {"abcdef", "3", "1 2 3"};
	private String[] outputData = {"fbdcea"};

	@Rule
	public Pipe pipe = new Pipe();
	
	@Test
	public void init() {
		pipe.prepare(inputData);
		
		SlowlyAlgorithm525B algorithm = (new C525B()).new SlowlyAlgorithm525B();
		algorithm.init();
		
		assertArrayEquals(algorithm.s, new char[] {'a', 'b', 'c', 'd', 'e', 'f'});
		assertEquals(algorithm.m, 3);
		assertArrayEquals(algorithm.ai, new int[] {1, 2, 3});
	}
	
	@Test
	public void run() {
		pipe.prepare(inputData);
		
		SlowlyAlgorithm525B algorithm = (new C525B()).new SlowlyAlgorithm525B();
		algorithm.init();
		algorithm.run();
		
		assertArrayEquals(algorithm.s, outputData[0].toCharArray());
	}
	
	@Test
	public void print() throws IOException {
		pipe.prepare(inputData);
		
		SlowlyAlgorithm525B algorithm = (new C525B()).new SlowlyAlgorithm525B();
		algorithm.init();
		algorithm.run();
		algorithm.print();
		
		assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
	}

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p525/B/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C525B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
    
	@Test
	public void arrays() {
		char[] s = {'a', 'b', 'c', 'd'};
		char[] tmpS = Arrays.copyOfRange(s, 0, s.length);
		assertArrayEquals(s, tmpS);
		//System.out.println("s = " + String.valueOf(s) + " | tmpS = " + String.valueOf(tmpS));
	}
	
	@Test
	@Ignore
	public void speed() {
		final int fMaxLengthString = 200000;
		final int fMaxNumberDays = 100000;
		
		char[] s = new char[fMaxLengthString];
		for (int index = 0; index < s.length; index++) {
			s[index] = 'a';
		}
		
		for (int day = 0; day < fMaxNumberDays; day++) {
			char[] tmpS = Arrays.copyOfRange(s, 0, s.length / 2);
			int idx = 0;
			for (int index = tmpS.length - 1; index >= 0; index--) {
				s[idx++] = tmpS[index];
			}
		}
	}

}
