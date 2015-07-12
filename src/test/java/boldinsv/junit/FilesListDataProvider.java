package boldinsv.junit;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.File;
import java.util.ArrayList;

public class FilesListDataProvider {

    private static Object[][] readFolder(String path) {
        File folder = new File(new File("").getAbsolutePath() + path);
        ArrayList<Answer> answers = new ArrayList<Answer>();

        for (String filename: folder.list()) {
            if (new File(folder.getAbsolutePath() + "/" + filename).isFile()) {
                answers.add(new Answer(folder.getAbsolutePath(), filename));
            }
        }

        Object[][] objects = new Object[answers.size()][1];
        int index = 0;

        for (Answer answer: answers) {
            objects[index][0] = answer;
            index += 1;
        }

        return objects;
    }

    @DataProvider
    public static Object[][] load(FrameworkMethod fmwMethod) {
        DataSource ds = fmwMethod.getAnnotation(DataSource.class);
        if (ds == null) {
            throw new Error("Test method has no @DataSource annotation: " + fmwMethod.getName());
        }
        return readFolder(ds.path());
    }


}
