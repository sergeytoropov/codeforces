package boldinsv.problems.p432.C;

import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static boldinsv.problems.p432.C.C432C.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

@RunWith(DataProviderRunner.class)
public class C432CTest {
    private C432C.Algorithm algorithm;

    private String[] inputData = new String[] {"4", "4 2 3 1"};
    private String[] outputData = new String[] {"3", "2 4", "1 2", "2 4"};
    private Integer[] simpleNumbers100 = new Integer[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void simpleNumbers() {
        ArrayList<Integer> array = C432C.simpleNumbers(100);

        assertEquals(array.size(), simpleNumbers100.length);
        assertEquals(array, new ArrayList<Integer>(Arrays.asList(simpleNumbers100)));
    }

    @Test
    public void init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.numbers.length, 4);
        assertArrayEquals(algorithm.numbers, new int[] {3, 1, 2, 0});

        assertEquals(algorithm.sortNumbers.length, 4);
        assertArrayEquals(algorithm.sortNumbers, new int[] {3, 1, 2, 0});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();

        ArrayList<String> answer = new ArrayList<String>();
        answer.add("2 4");
        answer.add("1 2");
        answer.add("2 4");

        assertEquals(algorithm.answer, answer);
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
    @DataSource(path = "/src/test/java/boldinsv/problems/p432/C/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C432C.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}