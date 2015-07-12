package boldinsv.problems.p508.A;

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
public class C508ATest {
    @Rule
    public Pipe pipe = new Pipe();

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p508/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C508A.main(null);

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
    @UseDataProvider(value = "load", location = C508ADataProvider.class)
    public void init(AnswersSet answers) throws IOException {
        prepare(answers);

        C508A.Init init = new C508A.Init();

        assertEquals(init.n, answers.n);
        assertEquals(init.m, answers.m);
        assertEquals(init.k, answers.k);
        assertArrayEquals(init.steps, answers.steps);
    }

    @Test
    @UseDataProvider(value = "load", location = C508ADataProvider.class)
    public void solve(AnswersSet answers) throws IOException {
        prepare(answers);

        C508A.Init init = new C508A.Init();
        C508A.Algoritm algoritm = new C508A.Algoritm(init.getGameDesk(), init.steps);
        assertEquals(algoritm.solve(), answers.result);
    }

    private void fillGameDesk(int[][] gameDesk, int status) {
        for (int i = 0; i < gameDesk.length; i++) {
            for (int j = 0; j < gameDesk[i].length; j++) {
                gameDesk[i][j] = status;
            }
        }
    }

    @Test
    public void gameDesk1x1() {
        int[][] gameDesk = new int[1][1];
        int x;
        int y;

        fillGameDesk(gameDesk, C508A.BLACK);
        C508A.Algoritm algoritm = new C508A.Algoritm(gameDesk, null);

        x = 0;
        y = 0;
        assertFalse(algoritm.topLeft2x2(x, y));
        assertFalse(algoritm.bottomLeft2x2(x, y));
        assertFalse(algoritm.topRight2x2(x, y));
        assertFalse(algoritm.bottomRight2x2(x, y));

        assertFalse(algoritm.is2x2(x, y));
    }

    @Test
    public void gameDesk2x2() {
        int[][] gameDesk = new int[2][2];

        fillGameDesk(gameDesk, C508A.BLACK);
        C508A.Algoritm algoritm = new C508A.Algoritm(gameDesk, null);

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (x == 1 && y == 1) {
                    assertTrue(algoritm.topLeft2x2(x, y));
                } else {
                    assertFalse(algoritm.topLeft2x2(x, y));
                }

                if (x == 0 && y == 1) {
                   assertTrue(algoritm.bottomLeft2x2(x, y));
                } else {
                    assertFalse(algoritm.bottomLeft2x2(x, y));
                }

                if (x == 1 && y == 0) {
                    assertTrue(algoritm.topRight2x2(x, y));
                } else {
                    assertFalse(algoritm.topRight2x2(x, y));
                }

                if (x == 0 && y == 0) {
                    assertTrue(algoritm.bottomRight2x2(x, y));
                } else {
                    assertFalse(algoritm.bottomRight2x2(x, y));
                }
            }
        }

        assertTrue(algoritm.is2x2(0, 0));
        assertTrue(algoritm.is2x2(0, 1));
        assertTrue(algoritm.is2x2(1, 0));
        assertTrue(algoritm.is2x2(1, 1));
    }

}
