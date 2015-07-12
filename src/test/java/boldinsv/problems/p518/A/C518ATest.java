package boldinsv.problems.p518.A;

import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C518ATest {
    private String[] inputData = {"aaaaa", "zzzzz"};
    private String[] outputData = {"baaaa"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        assertEquals(C518A.latin.length, 26);
        assertEquals(C518A.position.get('a'), new Integer(0));
        assertEquals(C518A.position.get('b'), new Integer(1));
        assertEquals(C518A.position.get('w'), new Integer(22));
        assertEquals(C518A.position.get('z'), new Integer(25));

        pipe.prepare(inputData);

        C518A.init();

        assertArrayEquals(C518A.s, "aaaaa".toCharArray());
        assertArrayEquals(C518A.t, "zzzzz".toCharArray());
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C518A.init();
        C518A.run();

        assertArrayEquals(C518A.m, "baaaa".toCharArray());
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C518A.init();
        C518A.run();
        C518A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p518/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C518A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}