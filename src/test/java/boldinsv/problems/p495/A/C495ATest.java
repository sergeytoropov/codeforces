package boldinsv.problems.p495.A;

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

@RunWith(DataProviderRunner.class)
public class C495ATest {
    C495A.Init init;
    C495A.Algorithm algorithm;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] { "91" });
        init = new C495A.Init();
        assertArrayEquals(init.n, new int[] {9, 1});
    }

    @Test
    public void algorithm() {
        algorithm = new C495A.Algorithm(new int[] { 0, 0 });
        assertEquals(algorithm.run(), 4);
    }

    @Test
    public void equals() {
        assertTrue(C495A.equals(1, 0));
        assertTrue(C495A.equals(1, 1));
        assertFalse(C495A.equals(1, 2));
        assertTrue(C495A.equals(1, 3));
        assertTrue(C495A.equals(1, 4));
        assertFalse(C495A.equals(1, 5));
        assertFalse(C495A.equals(1, 6));
        assertTrue(C495A.equals(1, 7));
        assertTrue(C495A.equals(1, 8));
        assertTrue(C495A.equals(1, 9));
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p495/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C495A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}

// 03, 08, 09, 33, 38, 39, 73, 78, 79, 83, 88, 89, 93, 98, 99.