import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class HttpBinGetTest {

    private static final String getUrl = "http://www.httpbin.org/get";
    private static Logger LOG = LoggerFactory.getLogger(HttpBinGetTest.class.getSimpleName());


    @Test(groups = "group1")
    public void testGetDefault() {
        LOG.info("Test: testGetDefault");
        HttpResponse responseGet = HttpRequest
                .get(getUrl)
                .sendAndGetResponse();

        String requestMethod = responseGet.getRequestMethod();
        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        String responseBody = responseGet.getResponseBody();
        JSONObject responseJson = new JSONObject(responseBody);
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(requestMethod).as("Request method").isEqualTo("GET");
        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("Server").get(0)).as("Header - Server").isEqualTo("nginx");
        assertThat(headerFields.get("Access-Control-Allow-Origin").get(0)).as("Header - Access-Control-Allow-Origin").isEqualTo("*");
        assertThat(headerFields.get("Access-Control-Allow-Credentials").get(0)).as("Header - Access-Control-Allow-Credentials").isEqualTo("true");
        assertThat(headerFields.get("Connection").get(0)).as("Header - Connection").isEqualTo("keep-alive");
        assertThat(headerFields.get("Content-Length").get(0)).as("Header - Content-Length").isEqualTo("246");
        assertThat(headerFields.get("Content-Type").get(0)).as("Header - Content-Type").isEqualTo("application/json");


        JSONObject responseJsonArgs;
        if (((JSONObject) responseJson.get("args")).length() == 0) {
            responseJsonArgs = null;
        } else {
            responseJsonArgs = (JSONObject) responseJson.get("args");
        }
        assertThat(responseJsonArgs).as("Response JSON Args").isNull();

        String url = (String) responseJson.get("url");
        assertThat(url).as("Response url").isEqualTo(getUrl);

        JSONObject responseJsonHeaders = (JSONObject) responseJson.get("headers");
        String accept = (String) responseJsonHeaders.get("Accept");
        String userAgent = (String) responseJsonHeaders.get("User-Agent");
        String host = (String) responseJsonHeaders.get("Host");

        assertThat(accept).as("Response body header Accept").isEqualTo("text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        assertThat(userAgent).as("Response body header User-Agent").isEqualTo("Java/1.8.0_25");
        assertThat(host).as("Response body header Host").isEqualTo("www.httpbin.org");
    }

    @Test(groups = "group1")
    public void testGetDefault2() {
        LOG.info("Test: testGetDefault2");

    }
}
