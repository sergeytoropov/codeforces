package boldinsv.problems.p496.A;

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
public class C496ATest {
    C496A.Init init;
    C496A.Algoritm algoritm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] { "5", "1 2 3 4 5" });
        init = new C496A.Init();
        assertEquals(init.n, 5);
        assertArrayEquals(init.heights, new int[] { 1, 2, 3, 4, 5 });

        pipe.prepare(new String[] { "5", "1 2 3 7 8" });
        init = new C496A.Init();
        assertEquals(init.n, 5);
        assertArrayEquals(init.heights, new int[] { 1, 2, 3, 7, 8 });
    }

    @Test
    public void algoritm() {
        algoritm = new C496A.Algoritm(new int[] { 1, 2, 3, 7, 8 });
        assertEquals(algoritm.run(), 4);
    }


    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p496/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C496A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}