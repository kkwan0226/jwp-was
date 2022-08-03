package webserver.common;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HeaderTest {

    @Test
    void 주어진_HEADER_LINE_LIST로부터_HEADER_파싱_테스트() {
        List<String> headerLineList = List.of("Host: localhost:8080", "Connection: keep-alive", "Accept: */*");
        Header header = Header.from(headerLineList);

        assertAll(
                () -> assertThat(header.get("Host")).isEqualTo("localhost:8080"),
                () -> assertThat(header.get("Connection")).isEqualTo("keep-alive"),
                () -> assertThat(header.get("Accept")).isEqualTo("*/*")
        );
    }
}
