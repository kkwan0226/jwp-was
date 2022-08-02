package webserver.request;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {

    private static final int QUERY_STRING_INDEX = 1;
    private static final int PARAMETER_KEY_INDEX = 0;
    private static final int PARAMETER_VALUE_INDEX = 1;

    private static final String QUERY_STRING_START_CHARACTER = "?";
    private static final String REQUEST_URL_PATH_DELIMITER = "\\?";
    private static final String REQUEST_URL_PARAMETER_DELIMITER = "&";
    private static final String REQUEST_URL_KEY_VALUE_DELIMITER = "=";

    private final Map<String, String> queryParameterMap;

    private QueryParameter(final Map<String, String> queryParameterMap) {
        this.queryParameterMap = queryParameterMap;
    }

    public static QueryParameter from(final String requestUrl) {
        return new QueryParameter(parseQueryString(requestUrl));
    }

    private static Map<String, String> parseQueryString(final String requestUrl) {
        if (!requestUrl.contains(QUERY_STRING_START_CHARACTER)) {
            String queryParameter = requestUrl.split(REQUEST_URL_PATH_DELIMITER)[QUERY_STRING_INDEX];
            String[] splitQueryParameter = queryParameter.split(REQUEST_URL_PARAMETER_DELIMITER);

            return getRequestParamMap(splitQueryParameter);
        }

        return new HashMap<>();
    }

    private static Map<String, String> getRequestParamMap(String[] splitQueryParameter) {
        Map<String, String> queryParameterMap = new HashMap<>();

        for (String parameter : splitQueryParameter) {
            String[] splitParameter = parameter.split(REQUEST_URL_KEY_VALUE_DELIMITER);
            queryParameterMap.put(splitParameter[PARAMETER_KEY_INDEX], splitParameter[PARAMETER_VALUE_INDEX]);
        }

        return queryParameterMap;
    }

    public String get(String key) {
        return this.queryParameterMap.get(key);
    }
}
