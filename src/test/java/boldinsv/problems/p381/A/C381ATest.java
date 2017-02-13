package boldinsv.problems.p381.A;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import boldinsv.problems.p501.A.C501A;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C381ATest {
    private String[] inputData = {"7", "1 2 3 4 5 6 7"};
    private String[] outputData = {"16 12"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C381A.init();

        Deque<Integer> numbers = new LinkedList<Integer>();
        for (int index = 1; index <= 7; index++) {
            numbers.add(index);
        }

        assertEquals(C381A.n, 7);
        assertEquals(C381A.numbers, numbers);
    }

    @Test
    public void run() {
        /*
        pipe.prepare(inputData);

        C381A.init();
        C381A.run();

        assertEquals(C381A.sumSergey, 16);
        assertEquals(C381A.sumDima, 12);
        */
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C381A.init();
        C381A.run();
        C381A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p381/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C381A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}