package webserver.response;

import org.junit.jupiter.api.Test;
import webserver.code.HttpStatus;
import webserver.common.Protocol;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StartLineTest {

    @Test
    void 올바른_START_LINE_생성_테스트() {
        Protocol protocol = Protocol.from("HTTP/1.1");
        StartLine startLine = StartLine.from(protocol, HttpStatus.ACCEPTED);

        assertAll(
                () -> assertThat(startLine.getProtocol()).isEqualTo(protocol),
                () -> assertThat(startLine.getHttpStatus()).isEqualTo(HttpStatus.ACCEPTED)
        );
    }
}
