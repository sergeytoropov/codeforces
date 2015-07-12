package boldinsv.problems.p538.A;

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
public class C538ATest {
    private String[] inputData = {"CODEWAITFORITFORCES"};
    private String[] outputData = {"YES"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C538A.init();

        assertEquals(C538A.line, "CODEWAITFORITFORCES");
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C538A.init();
        pipe.disable();
        C538A.run();
        
        assertEquals(C538A.answer, "YES");
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C538A.init();
        C538A.run();
        C538A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p538/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C538A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}