package webserver.request;

import java.util.List;

public class HttpRequest {

    private static final String TEMPLATES_PATH = "./templates";
    private static final int START_LINE_INDEX = 0;
    private static final int HEADER_START_INDEX = 1;

    private final StartLine startLine;
    private final Header header;
    private final Body body;

    private HttpRequest(final StartLine startLine, final Header header) {
        this.startLine = startLine;
        this.header = header;
        this.body = null;
    }

    private HttpRequest(final StartLine startLine, final Header header, final Body body) {
        this.startLine = startLine;
        this.header = header;
        this.body = body;
    }

    public static HttpRequest from(List<String> multiLine) {
        StartLine startLine = StartLine.from(multiLine.get(START_LINE_INDEX));
        Header header = Header.from(multiLine.subList(HEADER_START_INDEX, multiLine.size()));

        return new HttpRequest(startLine, header);
    }

    public static HttpRequest of(HttpRequest httpRequest, Body body) {
        return new HttpRequest(httpRequest.startLine, httpRequest.header, body);
    }

    public String getFilePath() {
        return TEMPLATES_PATH + this.startLine.getPath().getPath();
    }

    public StartLine getStartLine() {
        return this.startLine;
    }

    public Body getBody() {
        return this.body;
    }
}
