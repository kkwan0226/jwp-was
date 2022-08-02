package utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class IOUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(IOUtilsTest.class);

    @Test
    public void readData() throws Exception {
        String data = "abcd123";
        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);

        logger.debug("parse body : {}", IOUtils.readData(br, data.length()));
    }

    @Test
    public void 올바른_BufferedReader_테스트() throws Exception {
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        String[] expected = {"GET /index.html HTTP/1.1", "Host: localhost:8080", "Connection: keep-alive", "Accept: */*", null};
        when(bufferedReader.readLine())
                .thenReturn("GET /index.html HTTP/1.1")
                .thenReturn("Host: localhost:8080")
                .thenReturn("Connection: keep-alive")
                .thenReturn("Accept: */*")
                .thenReturn(null);

        List<String> multiLine = IOUtils.readMultiLine(bufferedReader);

        assertThat(multiLine).containsExactly(expected);
    }
}
