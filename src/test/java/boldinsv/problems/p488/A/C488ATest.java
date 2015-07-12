package boldinsv.problems.p488.A;

import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static boldinsv.problems.p488.A.C488A.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C488ATest {
    Algorithm algorithm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(new String[] {"-1"});
        algorithm = new Algorithm();
        algorithm.read().init();

        assertEquals(algorithm.getA(), -1);
    }

    @Test
    public void run() throws IOException {
        algorithm = new Algorithm(179);
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), new String[] {"1"});
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p488/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C488A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}