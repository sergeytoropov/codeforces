package boldinsv.junit;

import org.junit.rules.ExternalResource;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Pipe extends ExternalResource {
    private InputStream in;
    private PrintStream out;
    private ByteArrayOutputStream baos;
    private PrintStream pStream;

    @Override
    protected void before() throws Throwable {
        super.before();

        in = System.in;
        System.setIn(new ByteArrayInputStream("".getBytes()));

        baos = new ByteArrayOutputStream();
        pStream = new PrintStream(baos);
        out = System.out;
        System.setOut(pStream);
    }

    @Override
    protected void after() {
        super.after();

        disable();
        try {
            baos.close();
        }
        catch (IOException ioe) {
        }
    }

    public void disable() {
        System.setOut(out);
        System.setIn(in);
    }

    public void prepare(ArrayList<String> inputData) {
        StringBuffer buffer = new StringBuffer("");

        for (String line: inputData) {
            buffer.append(line).append("\n");
        }
        System.setIn(new ByteArrayInputStream(buffer.toString().getBytes()));
    }

    public void prepare(String[] inputData) {
        ArrayList<String> array = new ArrayList<String>();

        for (String line: inputData) {
            array.add(line);
        }
        prepare(array);
    }

    public ArrayList<String> getAnswerData() throws IOException {
        ArrayList<String> answer = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new StringReader(baos.toString()));
        String line;
        while ((line = reader.readLine()) != null) {
            answer.add(line);
        }
        reader.close();

        return answer;
    }

    public String[] getAnswerDataStringArray() throws IOException {
        ArrayList<String> answer = getAnswerData();
        return answer.toArray(new String[answer.size()]);
    }
}
