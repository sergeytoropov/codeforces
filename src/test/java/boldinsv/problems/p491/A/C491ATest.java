package boldinsv.problems.p491.A;

import boldinsv.junit.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static boldinsv.problems.p491.A.C491A.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C491ATest {
    private Init init;
    private Algorithm algorithm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] {"2", "1"});
        init = new Init();
        assertEquals(init.a, 2);
        assertEquals(init.b, 1);
    }

    @Test
    public void algorithm() throws IOException {
        algorithm = new Algorithm(2, 1);
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), new String[] {"1 3 4 2"});
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p491/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C491A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}