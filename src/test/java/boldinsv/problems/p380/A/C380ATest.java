package boldinsv.problems.p380.A;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static boldinsv.problems.p380.A.C380A.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C380ATest {
    private Algorithm algorithm;

    private String[] inputData = new String[] {"6", "1 1", "1 2", "2 2 1", "1 3", "2 5 2", "1 4", "16", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16"};
    private String[] outputData = new String[] {"1 2 1 2 3 1 2 1 2 3 1 2 1 2 3 4"};
    private long[][] table = new long[][] {
            {1, 1, 2, 1, 2, 1},
            {1, 2, 0, 3, 0, 4},
            {0, 0, 2, 0, 5, 0},
            {0, 0, 1, 0, 2, 0},
            {1, 2, 4, 5, 15, 16}
    };
    private long[] sequence = new long[] {1, 2, 1, 2, 3};
    private long[] answer = new long[] {1, 2, 1, 2, 3, 1, 2, 1, 2, 3, 1, 2, 1, 2, 3, 4};
    private long[] indexes = new long[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private long[] amount = new long[] {1, 2, 4, 5, 15, 16};

    // Test 2

    private String[] inputData2 = new String[] {"8", "1 14501", "2 1 1", "2 1 2", "2 1 4", "2 1 8", "2 9 1", "2 24 1", "2 27 1", "3", "3 58 67"};
    private String[] outputData2 = new String[] {"14501 14501 14501"};
    private long[][] table2 = new long[][] {
            {1, 2, 2, 2, 2, 2, 2, 2},
            {14501, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 9, 24, 27},
            {0, 1, 2, 4, 8, 1, 1, 1},
            {1, 2, 4, 8, 16, 25, 49, 76},
    };
    private long[] indexes2 = new long[] {3, 58, 67};
    private long[] sequence2;
    private long[] answer2 = new long[] {14501, 14501, 14501};
    private long[] amount2 = new long[] {1, 2, 4, 8, 16, 25, 49, 76};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.sequenceLength, 5);
        assertArrayEquals(algorithm.table, table);
        assertArrayEquals(algorithm.indexes, indexes);
    }

    @Test
    public void init2() {
        pipe.prepare(inputData2);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.sequenceLength, 27);
        assertArrayEquals(algorithm.table, table2);
        assertArrayEquals(algorithm.indexes, indexes2);
    }

    @Test
    public void bSearch() {
        // amount = new long[] {1, 2, 4, 5, 15, 16};
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 1), 0);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 2), 1);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 3), 2);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 4), 2);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 5), 3);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 6), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 7), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 8), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 9), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 10), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 11), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 12), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 13), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 14), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 15), 4);
        assertEquals(Algorithm.bSearch(amount, 0, amount.length - 1, 16), 5);
    }

    @Test
    public void bSearch2() {
        //amount2 = new long[] {1, 2, 4, 8, 16, 25, 49, 76};
        assertEquals(Algorithm.bSearch(amount2, 0, amount2.length - 1, 3), 2);
        assertEquals(Algorithm.bSearch(amount2, 0, amount2.length - 1, 58), 7);
        assertEquals(Algorithm.bSearch(amount2, 0, amount2.length - 1, 67), 7);
    }

    @Test
    public void createSequence() {
        pipe.prepare(inputData);
        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.createSequence();

        assertArrayEquals(algorithm.sequence, sequence);
    }

    @Test
    public void createSequence2() {
        pipe.prepare(inputData2);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.createSequence();

        assertEquals(algorithm.sequenceLength, 27);

        sequence2 = new long[algorithm.sequenceLength];
        for (int i = 0; i < sequence2.length; i++) {
            sequence2[i] = 14501;
        }

        assertArrayEquals(algorithm.sequence, sequence2);
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
    }

    @Test
    public void answer() throws IOException {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    //@Ignore
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p380/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C380A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }



    /*
    * Изучение передачи массивов в качестве параметров
    *
    * */

    private void initArray(int[] a) {
        for (int index = 0; index < a.length; index++) {
            a[index] = index + 1;
        }
    }

    private void printArray(int[] a) {
        for (int index = 0; index < a.length; index++) {
            System.out.print(a[index] + " ");
        }
        System.out.println();
    }

    private void initMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = i + 1;
            }
        }
    }

    private void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void arrays() {
        int[][] a = new int[10][5];

        initMatrix(a);
        printMatrix(a);
        System.out.println();

        printArray(a[9]);
    }
}