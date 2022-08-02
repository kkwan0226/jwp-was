package webserver;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RequestParamTest {

    @Test
    void 주어진_경로로부터_Query_String_파싱_테스트() {
        String requestUrl = "/users?userId=kkwan0226&password=password&name=kkwan";
        RequestParam requestParam = RequestParam.from(requestUrl);

        assertAll(
                () -> assertThat(requestParam.get("userId")).isEqualTo("kkwan0226"),
                () -> assertThat(requestParam.get("password")).isEqualTo("password"),
                () -> assertThat(requestParam.get("name")).isEqualTo("kkwan")
        );
    }
}
