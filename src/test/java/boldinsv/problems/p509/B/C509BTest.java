package boldinsv.problems.p509.B;

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

import static boldinsv.problems.p509.B.C509B.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C509BTest {
    private String[] inputData = {"4 4", "1 2 3 4"};
    private String[] outputData = {
            "YES",
            "1",
            "1 1",
            "1 1 2",
            "1 1 2 3"
    };

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C509B.init();

        assertEquals(C509B.n, 4);
        assertEquals(C509B.k, 4);
        assertArrayEquals(C509B.ai, new int[] {1, 2, 3, 4});
    }

    @Test
    public void max() {
        pipe.prepare(inputData);

        C509B.init();

        assertEquals(C509B.max(), 4);
    }

    @Test
    public void prepare() {
        pipe.prepare(inputData);

        C509B.init();
        C509B.prepare();

        Ball[][] board = new Ball[][] {
                {new Ball(false, 1, 1), new Ball(), new Ball(), new Ball()},
                {new Ball(false, 1, 1), new Ball(false), new Ball(), new Ball()},
                {new Ball(false, 1, 1), new Ball(false), new Ball(false), new Ball()},
                {new Ball(false, 1, 1), new Ball(false), new Ball(false), new Ball(false)},
        };

        assertArrayEquals(C509B.board, board);
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C509B.init();
        C509B.run();

        Ball[][] board = new Ball[][] {
                {new Ball(false, 1, 1), new Ball(true, 1, 1), new Ball(true, 2, 0), new Ball(true, 3, 0)},
                {new Ball(false, 1, 1), new Ball(false, 1, 2), new Ball(true, 2, 0), new Ball(true, 3, 0)},
                {new Ball(false, 1, 1), new Ball(false, 1, 2), new Ball(false, 2, 1), new Ball(true, 3, 0)},
                {new Ball(false, 1, 1), new Ball(false, 1, 2), new Ball(false, 2, 1), new Ball(false, 3, 1)},
        };

        assertArrayEquals(C509B.board, board);
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C509B.init();
        C509B.run();
        C509B.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p509/B/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C509B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}