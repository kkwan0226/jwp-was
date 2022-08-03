package webserver.request;

import org.junit.jupiter.api.Test;
import webserver.code.HttpMethod;
import webserver.common.Protocol;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StartLineTest {

    @Test
    void GET_요청에_대한_RequestLine_파싱_테스트() {
        String request = "GET /users HTTP/1.1";

        StartLine startLine = StartLine.from(request);
        HttpMethod httpMethod = startLine.getHttpMethod();
        Path path = startLine.getPath();
        Protocol protocol = startLine.getProtocolVersion();

        assertAll(
                () -> assertThat(httpMethod.getMethod()).isEqualTo("GET"),
                () -> assertThat(path.getPath()).isEqualTo("/users"),
                () -> assertThat(protocol.getName()).isEqualTo("HTTP"),
                () -> assertThat(protocol.getVersion()).isEqualTo("1.1")
        );
    }

    @Test
    void POST_요청에_대한_RequestLine_파싱_테스트() {
        String startLine = "POST /users HTTP/1.1";

        StartLine requestLine = StartLine.from(startLine);
        HttpMethod httpMethod = requestLine.getHttpMethod();
        Path path = requestLine.getPath();
        Protocol protocol = requestLine.getProtocolVersion();

        assertAll(
                () -> assertThat(httpMethod.getMethod()).isEqualTo("POST"),
                () -> assertThat(path.getPath()).isEqualTo("/users"),
                () -> assertThat(protocol.getName()).isEqualTo("HTTP"),
                () -> assertThat(protocol.getVersion()).isEqualTo("1.1")
        );
    }

    @Test
    void 요청의_Query_String으로_전달되는_데이터_파싱_테스트() {
        String request = "GET /users?userId=kkwan0226&password=password&name=kkwan HTTP/1.1";

        StartLine startLine = StartLine.from(request);
        QueryParameter queryParameter = startLine.getQueryParameter();

        assertAll(
                () -> assertThat(queryParameter.get("userId")).isEqualTo("kkwan0226"),
                () -> assertThat(queryParameter.get("password")).isEqualTo("password"),
                () -> assertThat(queryParameter.get("name")).isEqualTo("kkwan")
        );
    }
}
