package webserver.code;

import java.util.Arrays;

public enum HttpMethod {
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public static HttpMethod from(String method) {
        return Arrays.stream(values())
                .filter(value -> value.method.equals(method))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 못한 HTTP METHOD 입니다."));
    }

    public String getMethod() {
        return this.method;
    }
}
