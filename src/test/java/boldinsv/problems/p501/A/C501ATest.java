package boldinsv.problems.p501.A;

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

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C501ATest {
    @Rule
    public Pipe pipe = new Pipe();

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p501/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C501A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

    @Test
    public void max() {
        assertEquals(C501A.max(500, 20), 460);
        assertEquals(C501A.max(500, 200), 150);

        assertEquals(C501A.max(1000, 30), 880);
        assertEquals(C501A.max(1000, 200), 300);
    }
}