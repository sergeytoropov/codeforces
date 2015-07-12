package boldinsv.problems.p44.B;

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
public class C44BTest {

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p44/B/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C44B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}
