package boldinsv.problems.p490.A;

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

@RunWith(DataProviderRunner.class)
public class C490ATest {
    C490A.Init init;
    C490A.Algorithm algorithm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[]{"7", "1 3 1 3 2 1 2"});
        init = new C490A.Init();
        assertEquals(init.n, 7);
        assertArrayEquals(init.persons, new int[] {0, 2, 0, 2, 1, 0, 1});
    }

    @Test
    public void min() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    if (i == 1 && j == 1 && k == 1) {
                        continue;
                    }
                    assertEquals(C490A.min(i, j, k), 0);
                }
            }
        }
    }

    @Test
    public void algorithm() throws IOException {
        algorithm = new C490A.Algorithm(new int[] {0, 2, 0, 2, 1, 0, 1});
        algorithm.run();
        algorithm.answer().print();

        ArrayList<String> answer = new ArrayList<String>();
        answer.add("2");
        answer.add("1 5 2");
        answer.add("3 7 4");

        assertEquals(pipe.getAnswerData(), answer);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p490/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C490A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}