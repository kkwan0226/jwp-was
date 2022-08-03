package webserver.request;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Header {

    private static final String HEADER_DELIMITER = ": ";

    private static final int HEADER_KEY_INDEX = 0;
    private static final int HEADER_VALUE_INDEX = 1;

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
}
