package boldinsv.problems.p540.A;

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
public class C540ATest {
    private String[] inputData = {"5", "82195", "64723"};
    private String[] outputData = {"13"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C540A.init();

        assertEquals(C540A.n, 5);
        assertArrayEquals(C540A.code, new int[] {8, 2, 1, 9, 5});
        assertArrayEquals(C540A.answerCode, new int[] {6, 4, 7, 2, 3});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C540A.init();
        C540A.run();

        assertEquals(C540A.count, 13);
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C540A.init();
        C540A.run();
        C540A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p540/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C540A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

}