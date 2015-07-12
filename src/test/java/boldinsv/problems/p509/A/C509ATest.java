package boldinsv.problems.p509.A;

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
public class C509ATest {
    C509A.Init init;
    int[][] matrix;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] {"1"});
        init = new C509A.Init();
        assertEquals(init.n, 1);

        pipe.prepare(new String[] {"10"});
        init = new C509A.Init();
        assertEquals(init.n, 10);
    }

    @Test
    public void matrix() throws IOException {
        pipe.prepare(new String[] {"1"});
        matrix = new int[][] { {1} };
        init = new C509A.Init();
        assertArrayEquals(init.createMatrix(), matrix);

        pipe.prepare(new String[] {"2"});
        matrix = new int[][] { {1, 1}, {1, 2} };
        init = new C509A.Init();
        assertArrayEquals(init.createMatrix(), matrix);

        pipe.prepare(new String[] {"3"});
        matrix = new int[][] { {1, 1, 1}, {1, 2, 3}, {1, 3, 6} };
    }

    @Test
    public void run() throws IOException {
        matrix = new int[][] { {1, 1, 1}, {1, 2, 3}, {1, 3, 6} };

        C509A.Algoritm algoritm = new C509A.Algoritm(matrix);
        assertEquals(algoritm.run(), 6);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p509/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C509A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}