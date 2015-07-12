package boldinsv.problems.p489.A;

import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Arrays;

import static boldinsv.problems.p489.A.C489A.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C489ATest {
    private Init init;
    private Algorithm algorithm;

    private String[] inputData = new String[] {"5", "5 2 5 1 4"};
    private long[] longInputData = new long[] {5, 2, 5, 1, 4};
    private String[] outputData = new String[] {"2", "0 3", "2 4"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(inputData);
        init = new Init();
        assertEquals(init.n, 5);
        assertArrayEquals(init.array, longInputData);
    }

    @Test
    //@Ignore
    public void algorithm() throws IOException {
        //pipe.disable();
        algorithm = new Algorithm(longInputData);
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p489/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C489A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}