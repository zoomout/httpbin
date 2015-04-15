import java.util.List;
import java.util.Map;

public class HttpResponse {
    private int responseCode;
    private String responseMessage;
    private String responseBody;
    private String requestMethod;
    private Map<String, List<String>> headerFields;

    public HttpResponse(final int responseCode, final String responseMessage, final String responseBody,
                        final String requestMethod, final Map<String, List<String>> headerFields) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
        this.requestMethod = requestMethod;
        this.headerFields = headerFields;
    }

    public final Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public final String getRequestMethod() {
        return requestMethod;
    }

    public final String getResponseMessage() {
        return responseMessage;
    }

    public final int getResponseCode() {
        return responseCode;
    }

    public final String getResponseBody() {
        return responseBody;
    }
}
