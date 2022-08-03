package webserver.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BodyTest {

    @Test
    void 주어진_REQUEST_BODY로부터_사용자_정보_파싱_테스트() {
        String requestBody = "userId=kkwan0226&password=password&name=kkwan&email=kkwan%40airi.kr";
        Body body = Body.from(requestBody);

        assertAll(
                () -> assertThat(body.get("userId")).isEqualTo("kkwan0226"),
                () -> assertThat(body.get("password")).isEqualTo("password"),
                () -> assertThat(body.get("name")).isEqualTo("kkwan"),
                () -> assertThat(body.get("email")).isEqualTo("kkwan%40airi.kr")
        );
    }
}
