package boldinsv.problems.p479.B;

import boldinsv.junit.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static boldinsv.problems.p479.B.C479B.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C479BTest {
    private Algorithm algorithm;

    private String[] inputData = new String[] {"3 2", "5 8 5"};
    private String[] outputData = new String[] {"0 2", "2 1", "2 3"};

    private boolean board[][] = new boolean[][] {
            {false, true, false},
            {false, true, false},
            {false, true, false},
            {true, true, true},
            {true, true, true},
            {true, true, true},
            {true, true, true},
            {true, true, true}
    };

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.amountSteps, 2);
        assertArrayEquals(algorithm.board, board);
    }

    @Test
    public void navigation() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        Position top = algorithm.top(new Position(0, 0));
        assertEquals(top, new Position(0, 1));

        assertEquals(algorithm.max(top), 7);
        assertEquals(algorithm.max(new Position(0, 0)), 8);

        Position bottom = algorithm.bottom(new Position(7, 0));
        assertEquals(bottom, new Position(2, 0));

        assertEquals(algorithm.min(bottom), 5);
        assertEquals(algorithm.min(new Position(2, 2)), 6);

    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();

        //pipe.disable();

        for (Record r: algorithm.answer) {
            System.out.println(r);
        }
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p479/B/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C479B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}