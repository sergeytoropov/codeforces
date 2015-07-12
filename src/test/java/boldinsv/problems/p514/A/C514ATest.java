package boldinsv.problems.p514.A;

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
public class C514ATest {
    C514A.Init init;
    C514A.Algoritm algoritm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] { "27" });
        init = new C514A.Init();
        assertArrayEquals(init.digit, new int[] {2, 7});

        pipe.prepare(new String[] { "420062703497" });
        init = new C514A.Init();
        assertArrayEquals(init.digit, new int[] { 4, 2, 0, 0, 6, 2,7, 0, 3, 4, 9, 7 });
    }

    @Test
    public void algoritm() {
        algoritm = new C514A.Algoritm(new int[] { 2, 7 });
        algoritm.run();
        assertEquals(algoritm.toString(), "22");

        algoritm = new C514A.Algoritm(new int[] { 7, 7 });
        algoritm.run();
        assertEquals(algoritm.toString(), "22");
    }


    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p514/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C514A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}