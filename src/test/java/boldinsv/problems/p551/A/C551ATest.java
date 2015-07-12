package boldinsv.problems.p551.A;

import boldinsv.junit.Pipe;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class C551ATest {
    private String[] inputData = {"5", "3 5 3 4 5"};
    private String[] outputData = {"4 1 4 3 1"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C551A.init();

        assertEquals(C551A.n, 5);
        assertArrayEquals(C551A.ai, new int[] {3, 5, 3, 4, 5});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C551A.init();
        C551A.run();

        assertArrayEquals(C551A.result, new int[] {4, 1, 4, 3, 1});
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C551A.init();
        C551A.run();
        C551A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }
}