package webserver.request;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}
