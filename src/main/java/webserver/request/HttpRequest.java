package webserver.request;

import java.util.List;

public class HttpRequest {

    private static final String TEMPLATES_PATH = "./templates";
    private static final int START_LINE_INDEX = 0;
    private static final int HEADER_START_INDEX = 1;

    private final StartLine startLine;
    private final Header header;

    private HttpRequest(final StartLine startLine, final Header header) {
        this.startLine = startLine;
        this.header = header;
    }

    public static HttpRequest from(List<String> multiLine) {
        StartLine startLine = StartLine.from(multiLine.get(START_LINE_INDEX));
        Header header = Header.from(multiLine.subList(HEADER_START_INDEX, multiLine.size()));

        return new HttpRequest(startLine, header);
    }

    public String getFilePath() {
        return TEMPLATES_PATH + this.startLine.getPath().getPath();
    }
}
