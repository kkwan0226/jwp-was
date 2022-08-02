package webserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ProtocolTest {

    @Test
    void 올바른_REQUEST_PROTOCOL_VERSION_테스트() {
        String requestProtocolVersion = "HTTP/1.1";
        Protocol protocol = Protocol.from(requestProtocolVersion);

        assertAll(
                () -> Assertions.assertThat(protocol.getName()).isEqualTo("HTTP"),
                () -> Assertions.assertThat(protocol.getVersion()).isEqualTo("1.1")
        );
    }
}
