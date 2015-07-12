package boldinsv.junit;

import java.io.*;
import java.util.ArrayList;

public class Answer {
    private String absolutePath;
    private String filename;

    private ArrayList<String> inputData = null;
    private ArrayList<String> actualData = null;

    public Answer(String absolutePath, String filename) {
        this.absolutePath = absolutePath;
        this.filename = filename;
    }

    @Override
    public String toString() {
        String s = filename;
        if (absolutePath.indexOf("/problems") >= 0) {
            s = "..." + absolutePath.substring(absolutePath.indexOf("/problems")) + "/" + filename;
        }
        return s;
    }

    private void parseFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(absolutePath + "/" + filename)));

        final int stateInit = 0;
        final int stateInput = 2;
        final int stateActual = 3;
        int state = stateInit;

        String line;
        while ((line = reader.readLine()) != null) {

            if ("input".equals(line)) {
                state = stateInput;
                inputData = new ArrayList<String>();
                continue;
            }

            if ("output".equals(line)) {
                state = stateActual;
                actualData = new ArrayList<String>();
                continue;
            }

            if ("end".equals(line)) {
                break;
            }

            switch (state) {
                case stateInit:
                    break;

                case stateInput:
                    inputData.add(line);
                    break;

                case stateActual:
                    actualData.add(line);
                    break;
            }
        }
        reader.close();
    }

    public ArrayList<String> getInputData() throws IOException {
        if (inputData == null) {
            parseFile();
        }
        return inputData;
    }

    public ArrayList<String> getActualData() throws IOException {
        if (actualData == null) {
            parseFile();
        }
        return actualData;
    }
}
