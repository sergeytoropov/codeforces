package boldinsv.problems.p508.A;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

public class C508ADataProvider {

    @DataProvider
    public static Object[][] load(FrameworkMethod fmwMethod) {
        AnswersSet[] answers = AnswersSet.create();

        Object[][] objects = new Object[answers.length][1];
        for (int index = 0; index < answers.length; index++) {
            objects[index][0] = answers[index];
        }
        return objects;
    }
}
