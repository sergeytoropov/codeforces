package boldinsv.problems.p544.B;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C544BTest {
    private String[] inputData = {"5 4"};
    private String[] outputData = {"YES", "LSLSL", "SLSSS", "SSSSS", "SSSSS", "SSSSS"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C544B.init();

        assertEquals(C544B.n, 5);
        assertEquals(C544B.k, 4);
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C544B.init();
        C544B.run();

        assertTrue(C544B.answer);
        assertArrayEquals(C544B.map, new int[][] {{1, 0, 1, 0, 1}, {0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}});
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C544B.init();
        C544B.run();
        C544B.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p544/B/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C544B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}