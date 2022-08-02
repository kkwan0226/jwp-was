package webserver.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QueryParameterTest {

    @Test
    void 주어진_경로로부터_Query_String_파싱_테스트() {
        String request = "/users?userId=kkwan0226&password=password&name=kkwan";
        QueryParameter queryParameter = QueryParameter.from(request);

        assertAll(
                () -> assertThat(queryParameter.get("userId")).isEqualTo("kkwan0226"),
                () -> assertThat(queryParameter.get("password")).isEqualTo("password"),
                () -> assertThat(queryParameter.get("name")).isEqualTo("kkwan")
        );
    }
}
