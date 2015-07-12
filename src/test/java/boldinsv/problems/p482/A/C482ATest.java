package boldinsv.problems.p482.A;

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
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C482ATest {
    C482A.Init init;
    C482A.Algorithm algorithm;

    String[] inputData = new String[] { "5 2" };
    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(inputData);
        init = new C482A.Init();
        assertEquals(init.n, 5);
        assertEquals(init.k, 2);

        List<Integer> digitalRow = new LinkedList<Integer>();
        for (int index = 1; index <= 5; index++) {
            digitalRow.add(index);
        }
        assertEquals(init.getDigitalRow(), digitalRow);
    }

    @Test
    public void algorithm() throws IOException {
        pipe.prepare(inputData);
        //pipe.prepare(new String[] { "16 6" });
        init = new C482A.Init();

        List<Integer> moveRow = new LinkedList<Integer>();
        moveRow.add(1);
        moveRow.add(3);
        moveRow.add(2);
        moveRow.add(4);
        moveRow.add(5);

        pipe.disable();
        algorithm = new C482A.Algorithm(init.k, init.getDigitalRow());
        assertEquals(algorithm.run(), moveRow);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p482/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C482A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

}