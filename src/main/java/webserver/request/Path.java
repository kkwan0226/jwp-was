package webserver.request;

import java.util.Objects;

public class Path {

    private static final int PATH_INDEX = 0;
    private static final String QUERY_STRING_START_CHARACTER = "?";
    private static final String REQUEST_URL_PATH_DELIMITER = "\\?";

    private final String path;

    private Path(final String path) {
        this.path = path;
    }

    public static Path from(final String requestUrl) {
        if (requestUrl.contains(QUERY_STRING_START_CHARACTER)) {
            return new Path(requestUrl.split(REQUEST_URL_PATH_DELIMITER)[PATH_INDEX]);
        }

        return new Path(requestUrl);
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;
        Path path = (Path) o;
        return Objects.equals(getPath(), path.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath());
    }
}
