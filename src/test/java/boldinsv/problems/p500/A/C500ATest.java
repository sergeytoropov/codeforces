package boldinsv.problems.p500.A;

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
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C500ATest {
    @Rule
    public Pipe pipe = new Pipe();

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p500/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C500A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

    private ArrayList<String> toArrayList(String[] inputData) {
        ArrayList<String> array = new ArrayList<String>();

        for (String line: inputData) {
            array.add(line);
        }

        return array;
    }

    private void prepare(AnswersSet answers) {
        pipe.prepare(toArrayList(answers.getInput()));
    }

    @Test
    @UseDataProvider(value = "load", location = C500ADataProvider.class)
    public void init(AnswersSet answers) throws IOException {
        prepare(answers);

        C500A.Init init = new C500A.Init();

        assertEquals(init.n, answers.n);
        assertEquals(init.t, answers.t);
        assertArrayEquals(init.portals, answers.portals);
    }

    @Test
    @UseDataProvider(value = "load", location = C500ADataProvider.class)
    public void run(AnswersSet answers) {
        C500A.Algoritm algoritm = new C500A.Algoritm(answers.n, answers.t, answers.portals);
        assertEquals(algoritm.run(), answers.result);
    }
}