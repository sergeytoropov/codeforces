package boldinsv.problems.p494.A;

import boldinsv.junit.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import static boldinsv.problems.p494.A.C494A.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C494ATest {
    private Algorithm algorithm;
    private String[] inputData = {"()((#((#(#()"};
    private String[] outputData = {"2", "2", "1"};

    private String[] inputData2 = {"(((#)((#)"};
    private String[] outputData2 = {"2", "1"};

    private Deque<Character> message;

    @Rule
    public Pipe pipe = new Pipe();

    @Before
    public void initialize() {
        message = new LinkedList<Character>();
        message.add('(');
        message.add(')');
        message.add('(');
        message.add('(');
        message.add('#');
        message.add('(');
        message.add('(');
        message.add('#');
        message.add('(');
        message.add('#');
        message.add('(');
        message.add(')');
    }

    @Test
    public void init() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();

        assertEquals(algorithm.message, message);
        assertEquals(algorithm.answer.length, 3);
    }

    @Test
    public void swap() {
        Deque<Character> a = new LinkedList<Character>();
        Deque<Character> b = new LinkedList<Character>();

        Deque<Character> aSwap = a;
        Deque<Character> bSwap = b;

        algorithm = new Algorithm();
        algorithm.swap(aSwap, bSwap);

        assertTrue(bSwap == b);
        assertTrue(aSwap == a);

        assertFalse(bSwap == a);
        assertFalse(aSwap == b);
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();

        message.clear();
        message.offerLast('#');
        message.offerLast('#');
        message.offerLast('#');

        assertEquals(algorithm.message, message);
        assertEquals(algorithm.newMessage, message);
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
    public void print2() throws IOException {
        pipe.prepare(inputData2);

        algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData2);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p494/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C494A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100000; i++) {
            System.out.print("(");
        }
        System.out.println("#");
    }
}