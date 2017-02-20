package boldinsv.problems.p507.A;


import boldinsv.junit.Answer;
import boldinsv.junit.DataSource;
import boldinsv.junit.FilesListDataProvider;
import boldinsv.junit.Pipe;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class C507ATest {
    @Rule
    public Pipe pipe = new Pipe();

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
    @UseDataProvider(value = "load", location = C507ADataProvider.class)
    public void init(AnswersSet answers) throws IOException {
        prepare(answers);

        C507A.Init init = new C507A.Init();

        assertEquals(init.n, answers.n);
        assertEquals(init.k, answers.k);
        assertArrayEquals(init.musicalInstruments, answers.musicalInstruments);
    }

    @Test
    @UseDataProvider(value = "load", location = C507ADataProvider.class)
    public void sort(AnswersSet answers) {
        /*
        C507A.Algoritm algoritm = new C507A.Algoritm(answers.k, answers.orderMusicalInstruments);
        algoritm.sort();

        assertArrayEquals(algoritm.getMusicalInstruments(), answers.sortOrderMusicalInstruments);
        */
    }

    @Test
    @UseDataProvider(value = "load", location = C507ADataProvider.class)
    public void run(AnswersSet answers) {
        /*
        C507A.Algoritm algoritm = new C507A.Algoritm(answers.k, answers.orderMusicalInstruments);

        assertTrue(algoritm.run());
        assertEquals(algoritm.getQuantity(), answers.quantity);
        assertArrayEquals(algoritm.getOrder(), answers.order);
        */
    }

    @Test
    @UseDataProvider(value = "load", location = FilesListDataProvider.class)
    @DataSource(path = "/src/test/java/boldinsv/problems/p507/A/answers")
    public void main(Answer answer) throws IOException {
        pipe.prepare(answer.getInputData());

        C507A.main(null);

        assertEquals(pipe.getAnswerData(), answer.getActualData());
    }

    @Test
    public void hamcrestGeneralPurpose() {
        /*
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        assertThat(list, any(List.class));
        assertThat(list, instanceOf(ArrayList.class));
        assertThat(list, is(instanceOf(ArrayList.class)));
        assertThat(list, isA(List.class));

        //list = null;
        assertThat(null, nullValue());
        //assertThat(list, nullValue(List.class));
        //list2 = list;
        //assertThat(list, sameInstance(list2));
        //assertThat(list, theInstance(list2));

        list.add(5);
        list2.add(5);
        assertThat(list2, hasToString("[5]"));
        assertThat(list2, hasToString(is("[5]")));
        */

        System.out.println("Print");
        List<Integer> listInt = IntStream.range(0, 10).mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        listInt.stream().forEach(System.out::println);
    }
}