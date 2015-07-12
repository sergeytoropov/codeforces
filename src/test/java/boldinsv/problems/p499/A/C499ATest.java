package boldinsv.problems.p499.A;

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
public class C499ATest {
    private C499A.Init init;
    private C499A.Algoritm algoritm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] { "2 3", "5 6", "10 12" });
        init = new C499A.Init();
        assertEquals(init.n, 2);
        assertEquals(init.x, 3);
        assertArrayEquals(init.lr, new int[][] { {5, 6}, {10, 12} });

        pipe.prepare(new String[] { "1 1", "1 100000"} );
        init = new C499A.Init();
        assertEquals(init.n, 1);
        assertEquals(init.x, 1);
        assertArrayEquals(init.lr, new int[][] { {1, 100000} });
    }

    @Test
    public void run() {
        algoritm = new C499A.Algoritm(3, new int[][] { {5, 6}, {10, 12} });
        assertEquals(algoritm.run(), 6);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p499/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C499A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}