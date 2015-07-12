package boldinsv.problems.p432.A;

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
public class C432ATest {
    private String[] inputData = {"5 2", "0 4 5 1 0"};
    private String[] outputData = {"1"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C432A.init();

        assertEquals(C432A.n, 5);
        assertEquals(C432A.k, 2);
        assertArrayEquals(C432A.peoples, new int[] {0, 4, 5, 1, 0});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C432A.init();
        C432A.run();

        assertEquals(C432A.answer, 1);
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C432A.init();
        C432A.run();
        C432A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p432/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C432A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}