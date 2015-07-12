package boldinsv.problems.p496.B;

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
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C496BTest {
    C496B.Init init;
    C496B.Algorithm algorithm;
    C496B.Code code;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void init() throws IOException {
        pipe.prepare(new String[] { "4", "2014" });
        init = new C496B.Init();
        assertEquals(init.n, 4);
        assertEquals(init.value, "2014");
    }

    @Test
    public void code() {
        code = new C496B.Code("2014");
        assertArrayEquals(code.value, new int[] {2, 0, 1, 4});
        assertEquals(code.lastDigit(), 4);

        String[] answers;

        answers = new String[] { "3125", "4236", "5347", "6458", "7569", "8670", "9781", "0892", "1903", "2014" };
        for (int index = 0; index < answers.length; index++) {
            assertTrue(code.plusOne().equals(new C496B.Code(answers[index])));
        }

        answers = new String[] { "4201", "1420", "0142", "2014" };
        for (int index = 0; index < answers.length; index++) {
            assertTrue(code.shiftRight().equals(new C496B.Code(answers[index])));
        }

        code = new C496B.Code("");
        assertArrayEquals(code.plusOne().value, new int[] {});
        assertArrayEquals(code.shiftRight().value, new int[] {});
        assertEquals(code.lastDigit(), -1);

        code = new C496B.Code("0123456789");
        assertArrayEquals(code.value, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertEquals(code.lastDigit(), 9);
    }

    @Test
    public void codeCopmareTo() {
        C496B.Code a;
        C496B.Code b;

        a = new C496B.Code("2014");
        b = new C496B.Code("1420");
        assertTrue(a.compareTo(b) > 0);
        assertTrue(a.equals(a.clone()));
        assertTrue(b.equals(b.clone()));

        a = new C496B.Code("0142");
        b = new C496B.Code("1420");
        assertTrue(a.compareTo(b) < 0);
        assertTrue(a.equals(a.clone()));
        assertTrue(b.equals(b.clone()));

        a = new C496B.Code("0142");
        b = new C496B.Code("0142");
        assertTrue(a.compareTo(b) == 0);
        assertTrue(a.equals(a.clone()));
        assertTrue(b.equals(b.clone()));

        a = new C496B.Code("0020");
        b = new C496B.Code("0");
        assertTrue(a.compareTo(b) > 0);
        assertTrue(a.equals(a.clone()));
        assertTrue(b.equals(b.clone()));

        a = new C496B.Code("00012");
        b = new C496B.Code("00111");
        assertTrue(a.compareTo(b) < 0);
        assertTrue(a.equals(a.clone()));
        assertTrue(b.equals(b.clone()));

        a = new C496B.Code("0123456789876543210");
        b = new C496B.Code("123456789876543210");
        assertTrue(a.compareTo(b) == 0);
        assertTrue(a.equals(a.clone()));
        assertTrue(b.equals(b.clone()));
    }

    @Test
    public void copy() {
        int[] a = new int[] { 1 };
        int[] b = new int[a.length];
        C496B.Code.copy(a, b);
        assertArrayEquals(b, new int[] { 1 });

        a = new int[] { 1, 2, 3, 4, 5 };
        b = new int[a.length + 5];
        C496B.Code.copy(a, b);
        assertArrayEquals(b, new int[] { 0, 0, 0, 0, 0, 1, 2, 3, 4, 5 });
    }

    @Test
    public void algorithm() throws IOException {
        algorithm = new C496B.Algorithm(new C496B.Code("1"));
        algorithm.run();
        algorithm.answer().print();

        ArrayList<String> answer = new ArrayList<String>();
        answer.add("0");

        assertEquals(pipe.getAnswerData(), answer);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p496/B/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C496B.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}