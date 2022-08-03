package webserver.response;

import webserver.code.HttpStatus;
import webserver.common.Protocol;

public class StartLine {
    private final Protocol protocol;
    private final HttpStatus httpStatus;

    private StartLine(final Protocol protocol, final HttpStatus httpStatus) {
        this.protocol = protocol;
        this.httpStatus = httpStatus;
    }

    public static StartLine from(final Protocol protocol, final HttpStatus httpStatus) {
        return new StartLine(protocol, httpStatus);
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
