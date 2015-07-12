package boldinsv.problems.p505.A;

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
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class C505ATest {
    private C505A.Palyndrom palyndrom;

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p505/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C505A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

    @Test
    public void palyndromHasIt() {
        palyndrom = new C505A.Palyndrom("kitayuta");
        assertFalse(palyndrom.hasIt());

        palyndrom = new C505A.Palyndrom("reviver");
        assertTrue(palyndrom.hasIt());

        palyndrom = new C505A.Palyndrom("ee");
        assertTrue(palyndrom.hasIt());

        palyndrom = new C505A.Palyndrom("e");
        assertTrue(palyndrom.hasIt());

        palyndrom = new C505A.Palyndrom("eye");
        assertTrue(palyndrom.hasIt());

        palyndrom = new C505A.Palyndrom("");
        assertFalse(palyndrom.hasIt());
    }

    @Test
    public void palyndromIterator() {
        palyndrom = new C505A.Palyndrom("eye");
        String[] answers = new String[] {"ye", "ee", "ey"};
        char[] markedChars = new char[] {'e', 'y', 'e'};
        int index = 0;

        Iterator<C505A.Palyndrom> iter = palyndrom.iterator();
        while (iter.hasNext()) {
            C505A.Palyndrom p = iter.next();

            assertEquals(answers[index], p.getString());
            assertEquals(palyndrom.getMarkedChar(), markedChars[index]);
            assertEquals(palyndrom.getMarkedPosition(), index);
            index++;
        }
    }

    @Test
    public void palyndromCreate() {
        assertEquals(C505A.Palyndrom.create("evive".toCharArray(), 'r', 0), "reviver");
        assertEquals(C505A.Palyndrom.create("evive".toCharArray(), 'r', 5), "reviver");

        assertEquals(C505A.Palyndrom.create("reier".toCharArray(), 'v', 3), "reviver");
        assertEquals(C505A.Palyndrom.create("reier".toCharArray(), 'v', 2), "reviver");
    }

    @Test
    public void palyndromAddChar() {
        assertEquals(C505A.Palyndrom.addChar("e".toCharArray()), "ee");
        assertEquals(C505A.Palyndrom.addChar("ee".toCharArray()), "eye");
        assertEquals(C505A.Palyndrom.addChar("revver".toCharArray()), "revyver");
        assertEquals(C505A.Palyndrom.addChar("reviver".toCharArray()), "reviiver");
    }
}