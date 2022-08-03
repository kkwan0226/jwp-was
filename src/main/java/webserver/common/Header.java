package webserver.common;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Header {

    private static final String HEADER_DELIMITER = ": ";
    private static final String CONTENT_LENGTH = "Content-Length";

    private static final int HEADER_KEY_INDEX = 0;
    private static final int HEADER_VALUE_INDEX = 1;
    private static final int DEFAULT_CONTENT_LENGTH = 0;

    private final Map<String, String> headerMap;


    private Header(final Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public static Header from(final List<String> headerLineList) {
        return new Header(parseHeaderMap(headerLineList));
    }

    private static Map<String, String> parseHeaderMap(List<String> headerLineList) {
        return headerLineList.stream()
                .map(headerLine -> headerLine.split(HEADER_DELIMITER))
                .collect(Collectors.toMap(splitHeaderLine -> splitHeaderLine[HEADER_KEY_INDEX], splitHeaderLine -> splitHeaderLine[HEADER_VALUE_INDEX]));
    }

    public String get(String key) {
        return this.headerMap.get(key);
    }

    public int getContentLength() {
        if (headerMap.containsKey(CONTENT_LENGTH)) {
            return Integer.parseInt(this.headerMap.get(CONTENT_LENGTH));
        }

        return DEFAULT_CONTENT_LENGTH;
    }
}
