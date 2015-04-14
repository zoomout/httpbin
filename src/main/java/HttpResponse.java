import java.util.List;
import java.util.Map;

public class HttpResponse {
    private int responseCode;
    private String responseMessage;
    private String responseBody;
    private String requestMethod;
    private Map<String, List<String>> headerFields;

    public HttpResponse(int responseCode, String responseMessage, String responseBody, String requestMethod, Map<String, List<String>> headerFields) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
        this.requestMethod = requestMethod;
        this.headerFields = headerFields;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
