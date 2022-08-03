package webserver.request;

import java.util.HashMap;
import java.util.Map;

public class Body {

    private static final String BODY_DELIMITER = "&";
    private static final String KEY_VALUE_DELIMITER = "=";

    private static final int BODY_KEY_INDEX = 0;
    private static final int BODY_VALUE_INDEX = 1;

    private final Map<String, String> bodyMap;


    private Body(final Map<String, String> bodyMap) {
        this.bodyMap = bodyMap;
    }

    public static Body from(final String body) {
        return new Body(parseBody(body));
    }

    private static Map<String, String> parseBody(final String body) {
        String[] splitBody = body.split(BODY_DELIMITER);

        return getBodyMap(splitBody);
    }

    private static Map<String, String> getBodyMap(String[] splitBody) {
        Map<String, String> bodyMap = new HashMap<>();

        for (String data : splitBody) {
            String[] splitData = data.split(KEY_VALUE_DELIMITER);
            bodyMap.put(splitData[BODY_KEY_INDEX], splitData[BODY_VALUE_INDEX]);
        }

        return bodyMap;
    }

    public String get(String key) {
        return this.bodyMap.get(key);
    }
}
