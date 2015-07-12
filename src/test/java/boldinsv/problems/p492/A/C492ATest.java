package boldinsv.problems.p492.A;

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
public class C492ATest {
    C492A.Init init;
    C492A.Algorithm algorithm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] { "25" });
        init = new C492A.Init();
        assertEquals(init.n, 25);
    }

    @Test
    public void algorithm() {
        algorithm = new C492A.Algorithm(25);
        assertEquals(algorithm.run(), 4);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p492/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C492A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

}