package boldinsv.problems.p380.A;

import boldinsv.junit.*;
import boldinsv.junit.Answer;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static boldinsv.problems.p380.A.C380A3.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C380ATest3 {
    private Algorithm algorithm;

    private String[] inputData = new String[] {"6", "1 1", "1 2", "2 2 1", "1 3", "2 5 2", "1 4", "16", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16"};
    private String[] outputData = new String[] {"1 2 1 2 3 1 2 1 2 3 1 2 1 2 3 4"};

    private ArrayList<ItemSequence> tables;
    private LinkedList<Long> indexes;
    private ArrayList<Long> sequence;
    private LinkedList<Long> answer;

    @Rule
    public Pipe pipe = new Pipe();

    @Before
    public void initialize() {
        tables = new ArrayList<ItemSequence>();

        tables.add(new ItemSequence(1));
        tables.add(new ItemSequence(2));
        tables.add(new ItemSequence(2, 1));
        tables.add(new ItemSequence(3));
        tables.add(new ItemSequence(5, 2));
        tables.add(new ItemSequence(4));

        indexes = new LinkedList<Long>();
        for (long index = 0; index < 16; index++) {
            indexes.add(index);
        }

        sequence = new ArrayList<Long>();

        for (int index = 0; index < 2; index++) {
            sequence.add(1L);
            sequence.add(2L);
            sequence.add(1L);
            sequence.add(2L);
            sequence.add(3L);
        }

        answer = new LinkedList<Long>();

        for (int index = 0; index < 2; index++) {
            answer.add(1L);
            answer.add(2L);
            answer.add(1L);
            answer.add(2L);
            answer.add(3L);
        }
        answer.add(1L);
        answer.add(2L);
        answer.add(1L);
        answer.add(2L);
        answer.add(3L);
        answer.add(4L);
    }


    @Test
    public void init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.m, 6);
        assertEquals(algorithm.tables, tables);
        assertEquals(algorithm.n, 16);
        assertEquals(algorithm.indexes, indexes);
    }

    @Test
    public void itemSequence() {
        ItemSequence item = new ItemSequence(5, 5);

        while (item.hasDecrementCi()) {
            assertTrue(item.isSequence());
            assertFalse(item.isValue());
            assertEquals(item.decrementCi(), 5);
        }
        assertFalse(item.hasDecrementCi());

        item = new ItemSequence(4);
        assertTrue(item.isValue());
        assertFalse(item.isSequence());
        assertTrue(item.hasValue());
        assertEquals(item.value(), 4);
        assertFalse(item.hasValue());
    }

    @Test
    public void createSeq() {
        pipe.prepare(inputData);

        algorithm = new Algorithm(5);
        algorithm.data().init();

        algorithm.sequence.clear();
        algorithm.createSeq();

        assertEquals(algorithm.sequence, sequence);
        assertEquals(algorithm.maxIndex, 10);
        assertEquals(algorithm.begin, 0);
        assertEquals(algorithm.end, 9);
    }

    @Test
    public void run() throws IOException {
        pipe.prepare(inputData);

        algorithm = new Algorithm(5);
        algorithm.data().init();
        algorithm.run();

        assertEquals(algorithm.answer, answer);

        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p380/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C380A3.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}