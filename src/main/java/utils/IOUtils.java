package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    public static String readData(BufferedReader br, int contentLength) throws IOException {
        char[] body = new char[contentLength];
        br.read(body, 0, contentLength);
        return String.copyValueOf(body);
    }

    public static List<String> readMultiLine(BufferedReader bufferedReader) throws IOException {
        List<String> multiLine = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            multiLine.add(line);
        }

        return multiLine;
    }
}
