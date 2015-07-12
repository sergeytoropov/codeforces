package boldinsv.problems.p220.A;

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
public class C220ATest {
    private String[] inputData = {"3", "3 2 1"};
    private String[] outputData = {"YES"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C220A.init();

        assertEquals(C220A.n, 3);
        assertArrayEquals(C220A.sequence, new int[] {3, 2, 1});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C220A.init();
        C220A.run();

        assertEquals(C220A.answer, "YES");
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C220A.init();
        C220A.run();
        C220A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p220/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C220A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}