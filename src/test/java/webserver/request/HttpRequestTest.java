package webserver.request;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HttpRequestTest {

    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void HTTP_REQUEST_파일_경로_테스트() {
        HttpRequest httpRequest = HttpRequest.from(List.of("GET /index.html HTTP/1.1", "Host: localhost:8080", "Connection: keep-alive", "Accept: */*"));

        assertThat(httpRequest.getFilePath()).isEqualTo("./templates/index.html");
    }

    @Test
    void HTTP_REQUEST로부터_사용자_정보_파싱_테스트() {
        HttpRequest httpRequest = HttpRequest.from(List.of("GET /users?userId=kkwan0226&password=password&name=kkwan&email=kkwan%40airi.kr HTTP/1.1", "Host: localhost:8080", "Connection: keep-alive", "Accept: */*"));

        assertAll(
                () -> assertThat(httpRequest.getStartLine().getQueryParameter().get("userId")).isEqualTo("kkwan0226"),
                () -> assertThat(httpRequest.getStartLine().getQueryParameter().get("password")).isEqualTo("password"),
                () -> assertThat(httpRequest.getStartLine().getQueryParameter().get("name")).isEqualTo("kkwan"),
                () -> assertThat(httpRequest.getStartLine().getQueryParameter().get("email")).isEqualTo("kkwan%40airi.kr")
        );
    }
}
