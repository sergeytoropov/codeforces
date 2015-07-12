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
import java.util.Arrays;

import static boldinsv.problems.p432.C.C432C2.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C432CTest2 {
    private Algorithm algorithm;

    private String[] inputData = new String[] {"4", "4 2 3 1"};
    private int[] simpleNumbers100 = new int[] {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void simpleNumbers() {
        int[] array = C432C2.simpleNumbers(100);

        assertEquals(array.length, 26);
        assertArrayEquals(array, simpleNumbers100);
    }

    @Test
    public void binarySearch() {
        assertEquals(C432C2.binarySearch(simpleNumbers100, 1), 0);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 2), 1);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 3), 2);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 4), 2);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 5), 3);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 72), 20);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 73), 21);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 74), 21);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 97), 25);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 98), 25);
        assertEquals(C432C2.binarySearch(simpleNumbers100, 1001), 25);
    }

    @Test
    public void init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.numbers.length, 4);
        assertArrayEquals(algorithm.numbers, new int[] {3, 1, 2, 0});
        assertArrayEquals(algorithm.indexes, new int[] {3, 1, 2, 0});
    }

    @Test
    public void run_init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        algorithm.init();
        assertEquals(algorithm.sortNumbers.length, 4);
        assertArrayEquals(algorithm.sortNumbers, new int[] {0, 1, 2, 3});

//        assertEquals(algorithm.simpleNumbers.length, 1);
//        assertArrayEquals(algorithm.simpleNumbers, new int[] {1});
    }

    @Test
    public void run() throws IOException {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), new String[] {"3", "3 4", "1 3", "3 4"});
    }

    /*
    @Test
    public void search() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.search(0, 1), 3);
        assertEquals(algorithm.search(1, 3), 2);
        assertEquals(algorithm.search(2, 1), 3);
    }
    */

    @Test
    public void bSearch() {
        int[] array = new int[] {1, 4, 5, 7, 9};

        assertEquals(Arrays.binarySearch(array, 9), 4);
        assertEquals(Arrays.binarySearch(array, 1), 0);
        assertTrue(Arrays.binarySearch(array, 8) < 0);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p432/C/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C432C2.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}