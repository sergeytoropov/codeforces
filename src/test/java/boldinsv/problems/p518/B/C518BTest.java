package boldinsv.problems.p518.B;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


@RunWith(DataProviderRunner.class)
public class C518BTest {
    private C518B.Init init;
    private C518B.Algorithm algorithm;

    private String[] inputData = new String[] { "AbC", "DCbADCb" };
    private String word = "AbC";
    private Map<Character, Integer> newspaper;

    @Rule
    public Pipe pipe = new Pipe();

    @Before
    public void localInit() {
        newspaper = new HashMap<Character, Integer>();
        newspaper.put('D', 2);
        newspaper.put('C', 2);
        newspaper.put('b', 2);
        newspaper.put('A', 1);
    }

    @Test
    public void init() throws IOException {
        pipe.prepare(inputData);
        init = new C518B.Init();
        assertEquals(init.s, inputData[0]);
        assertEquals(init.t, inputData[1]);
        assertEquals(init.getNewspaper(), newspaper);
    }

    @Test
    public void algorithm() {
        algorithm = new C518B.Algorithm(word, newspaper);
        assertEquals(algorithm.run(), "3 0");
    }

    @Test
    public void algorithmInvert() {
        assertEquals(C518B.Algorithm.invert('a'), 'A');
        assertEquals(C518B.Algorithm.invert('A'), 'a');

        assertEquals(C518B.Algorithm.invert('z'), 'Z');
        assertEquals(C518B.Algorithm.invert('Z'), 'z');
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p518/B/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C518B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

}