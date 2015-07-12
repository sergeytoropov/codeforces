package boldinsv.problems.p519.B;

import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static boldinsv.problems.p519.B.C519B.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C519BTest {
    Algorithm algorithm;
    private String[] inputData = new String[] {"5", "1 5 8 123 7", "123 7 5 1", "5 1 7"};
    private String[] outputData = new String[] {"8", "123"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);
        algorithm = new Algorithm();
        algorithm.read().init();

        assertEquals(algorithm.getN(), 5);
        assertArrayEquals(algorithm.getAn(), new long[] {1, 5, 8, 123, 7});
        assertArrayEquals(algorithm.getBn(), new long[] {123, 7, 5, 1});
        assertArrayEquals(algorithm.getCn(), new long[] {5, 1, 7});

    }

    @Test
    public void run() throws IOException {
        algorithm = new Algorithm(new long[] {1, 5, 8, 123, 7}, new long[] {123, 7, 5, 1}, new long[] {5, 1, 7});
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p519/B/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C519B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}