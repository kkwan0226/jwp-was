package webserver;

import controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
import utils.IOUtils;
import webserver.request.Body;
import webserver.request.HttpRequest;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final Socket connection;
    private final UserController userController;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
        this.userController = new UserController();
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            HttpRequest httpRequest = httpRequest(bufferedReader);

            DataOutputStream dos = new DataOutputStream(out);

            userController.route(httpRequest);
            byte[] body = getBody(httpRequest);

            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        try {
            return FileIoUtils.loadFileFromClasspath(httpRequest.getFilePath());
        } catch (NullPointerException e) {
            return new byte[0];
        }
    }

    private HttpRequest httpRequest(BufferedReader bufferedReader) throws IOException {
        HttpRequest httpRequest = HttpRequest.from(IOUtils.readMultiLine(bufferedReader));

        if (httpRequest.getHeader().getContentLength() > 0) {
            return httpRequest.withBody(Body.from(IOUtils.readData(bufferedReader, httpRequest.getHeader().getContentLength())));
        }

        return httpRequest;
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
