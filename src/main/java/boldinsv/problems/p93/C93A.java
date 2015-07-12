package boldinsv.problems.p93;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C93A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] params = reader.readLine().split(" ");
        reader.close();
        
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int a = Integer.parseInt(params[2]);
        int b = Integer.parseInt(params[3]);
        
        int folders = n;
        int width = m;
        int beginIdx = a - 1;
        int endIdx = b - 1;
        
        if (endIdx + 1 == folders) {
            endIdx = (endIdx / width + 1) * width - 1;
        }
        
        int beginRow = beginIdx / width;
        int endRow = endIdx / width;
        int beginColumn = beginIdx % width;
        int endColumn = endIdx % width;
        
        int answer = 3;
        // Одна строка
        if (beginRow == endRow) {
            answer = 1;
        } else {
            // Одна целая область
            if (beginColumn == 0 && endColumn == width - 1) {
                answer = 1;
            } else {
                // Две строки
                if (beginRow + 1 == endRow) {
                    answer = 2;
                } else {
                    // Область начинается с нулевой колонки, область заканчивается последней колонкой, две колонки
                    if (beginColumn == 0 || endColumn == width - 1 || beginColumn - 1 == endColumn) {
                        answer = 2;
                    }
                }
            }
        }
        
        System.out.println("" + answer);
    }
}