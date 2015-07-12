package boldinsv.problems.p378.A;

import boldinsv.junit.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C378ATest {
    C378A.Algorithm object = new C378A.Algorithm();

    @Rule
    public Pipe pipe = new Pipe();

    @Test
    public void read() {
        pipe.prepare(new String[] {"2 5"});

        object.data().read();

        assertEquals(object.a, 2);
        assertEquals(object.b, 5);
    }

    @Test
    public void run() throws IOException {
        pipe.prepare(new String[] {"2 5"});

        object.data().read();
        object.run();
        object.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), new String[] {"3 0 3"});
    }


    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p378/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C378A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

}