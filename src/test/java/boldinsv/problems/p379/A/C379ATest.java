package boldinsv.problems.p379.A;

import boldinsv.junit.Pipe;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class C379ATest {
    private String[] inputData = new String[] {"4 2"};
    private String[] outputData = new String[] {"7"};
    private C379A algorithm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(inputData);

        algorithm = new C379A();
        algorithm.init();

        assertEquals(algorithm.a, 4);
        assertEquals(algorithm.b, 2);
    }

    @Test
    public void run() throws IOException {
        pipe.prepare(inputData);

        algorithm = new C379A();
        algorithm.init();
        algorithm.run();

        assertEquals(algorithm.answer, 7);
    }

    @Test
    public void run2() throws IOException {
        pipe.prepare(new String[] {"6 3"});

        algorithm = new C379A();
        algorithm.init();
        algorithm.run();

        assertEquals(algorithm.answer, 8);
    }


    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        algorithm = new C379A();
        algorithm.init();
        algorithm.run();
        algorithm.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }
}

