package boldinsv.problems.p493.A;

import boldinsv.junit.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static boldinsv.problems.p493.A.C493A.*;
import static boldinsv.problems.p493.A.C493A.Card.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class C493ATest {
    private Init init;
    private Algorithm algorithm;
    private final String[] inputData = new String[] {"MC", "CSKA", "9", "28 a 3 y", "62 h 25 y", "66 h 42 y", "70 h 25 y", "77 a 4 y", "79 a 25 y", "82 h 42 r", "89 h 16 y", "90 a 13 r"};
    private final String[] outputData = new String[] {"MC 25 70", "MC 42 82", "CSKA 13 90"};

    SoccerTeam houses;
    SoccerTeam guest;

    @Rule
    public Pipe pipe = new Pipe();

    @Before
    public void initTeams() {
        houses = new SoccerTeam("MC");
        guest = new SoccerTeam("CSKA");

        guest.addFoul(3, new Foul(28, YELLOW));
        houses.addFoul(25, new Foul(62, YELLOW));
        houses.addFoul(42, new Foul(66, YELLOW));
        houses.addFoul(25, new Foul(70, YELLOW));
        guest.addFoul(4, new Foul(77, YELLOW));
        guest.addFoul(25, new Foul(79, YELLOW));
        houses.addFoul(42, new Foul(82, RED));
        houses.addFoul(16, new Foul(89, YELLOW));
        guest.addFoul(13, new Foul(90, RED));
        //guest.addFoul(13, new Foul(90, YELLOW));
    }

    @Test
    public void init() throws IOException {
        pipe.prepare(inputData);
        init = new Init();
        assertEquals(init.houses, houses);
        assertEquals(init.guest, guest);
    }

    @Test
    public void algorithm() throws IOException {
        pipe.prepare(inputData);

        init = new Init();
        algorithm = new Algorithm(init.houses, init.guest);
        algorithm.run();
        algorithm.answer().print();

        assertArrayEquals(pipe.getAnswerDataStringArray(), outputData);
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p493/A/answers")
    public void main(boldinsv.junit.Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C493A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }
}