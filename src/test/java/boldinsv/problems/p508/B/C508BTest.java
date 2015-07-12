package boldinsv.problems.p508.B;

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
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C508BTest {
    private String[] inputData = {"4573"};
    private String[] outputData = {"3574"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C508B.init();
        assertArrayEquals(C508B.currency, new int[] {4, 5, 7, 3});
    }

    @Test
    public void odd() {
        C508B.found = false;
        C508B.currency = new int[] {1, 3, 5, 7, 9, 9, 7, 5, 3, 1};
        C508B.odd();

        assertFalse(C508B.found);

        C508B.found = false;
        C508B.currency = new int[] {5, 2, 7};
        C508B.odd();

        assertTrue(C508B.found);
        assertArrayEquals(C508B.currency, new int[] {5, 7, 2});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C508B.init();
        C508B.run();

        assertArrayEquals(C508B.currency, new int[] {3, 5, 7, 4});
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C508B.init();
        C508B.run();
        C508B.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p508/B/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C508B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

}