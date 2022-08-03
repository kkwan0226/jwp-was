package webserver.request;

import webserver.code.HttpMethod;

public class StartLine {

    private static final String EMPTY_STRING = " ";
    private static final int HTTP_METHOD_INDEX = 0;
    private static final int REQUEST_URL_INDEX = 1;
    private static final int PROTOCOL_VERSION_INDEX = 2;

    private final HttpMethod httpMethod;
    private final Path path;
    private final Protocol protocol;
    private final QueryParameter queryParameter;

    private StartLine(
            final HttpMethod httpMethod,
            final Path path,
            final Protocol protocol,
            final QueryParameter queryParameter
    ) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.protocol = protocol;
        this.queryParameter = queryParameter;
    }

    public static StartLine from(final String startLine) {
        String[] splitStartLine = startLine.split(EMPTY_STRING);
        HttpMethod httpMethod = HttpMethod.from(splitStartLine[HTTP_METHOD_INDEX]);
        Path path = Path.from(splitStartLine[REQUEST_URL_INDEX]);
        Protocol protocol = Protocol.from(splitStartLine[PROTOCOL_VERSION_INDEX]);
        QueryParameter requestParam = QueryParameter.from(splitStartLine[REQUEST_URL_INDEX]);

        return new StartLine(httpMethod, path, protocol, requestParam);
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public Path getPath() {
        return this.path;
    }

    public Protocol getProtocolVersion() {
        return this.protocol;
    }

    public QueryParameter getQueryParameter() {
        return this.queryParameter;
    }
}
