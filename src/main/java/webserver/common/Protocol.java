package webserver.common;

public class Protocol {

    private static final String REQUEST_PROTOCOL_VERSION_DELIMITER = "/";

    private static final int PROTOCOL_INDEX = 0;
    private static final int VERSION_INDEX = 1;

    private final String name;
    private final String version;

    private Protocol(final String name, final String version) {
        this.name = name;
        this.version = version;
    }

    public static Protocol from(final String requestProtocolVersion) {
        String[] splitRequestProtocolVersion = requestProtocolVersion.split(REQUEST_PROTOCOL_VERSION_DELIMITER);

        return new Protocol(splitRequestProtocolVersion[PROTOCOL_INDEX], splitRequestProtocolVersion[VERSION_INDEX]);
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }
}
