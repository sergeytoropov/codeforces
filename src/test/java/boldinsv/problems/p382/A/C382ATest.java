package boldinsv.problems.p382.A;

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
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C382ATest {
    private String[] inputData = {"AC|T", "L"};
    private String[] outputData = {"AC|TL"};

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() {
        pipe.prepare(inputData);

        C382A.init();

        List<Character> leftPart = new LinkedList<Character>();
        leftPart.add('A');
        leftPart.add('C');

        List<Character> rightPart = new LinkedList<Character>();
        rightPart.add('T');

        assertEquals(C382A.leftPart, leftPart);
        assertEquals(C382A.rightPart, rightPart);

        assertArrayEquals(C382A.symbols, new char[] {'L'});
    }

    @Test
    public void run() {
        pipe.prepare(inputData);

        C382A.init();
        C382A.run();

        assertTrue(C382A.leftPart.size() == C382A.rightPart.size());
    }

    @Test
    public void print() throws IOException {
        pipe.prepare(inputData);

        C382A.init();
        C382A.run();
        C382A.print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p382/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C382A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}