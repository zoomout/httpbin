import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;


public class HttpBinResponseHeadersTest extends HttpBinGeneral {

    private static final String responseHeadersUrl = "http://www.httpbin.org/response-headers";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinResponseHeadersTest.class.getSimpleName());

    @Test(groups = "group2")
    public void testResponseHeadersDefault() {
        LOG.info("Test: testResponseHeadersDefault");

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl)
                .sendAndGetResponse();

        String requestMethod = responseGet.getRequestMethod();
        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(requestMethod).as("Request method").isEqualTo("GET");
        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("Server").get(0)).as("Header - Server").isEqualTo("nginx");
        assertThat(headerFields.get("Access-Control-Allow-Origin").get(0)).as("Header - Access-Control-Allow-Origin").isEqualTo("*");
        assertThat(headerFields.get("Access-Control-Allow-Credentials").get(0)).as("Header - Access-Control-Allow-Credentials").isEqualTo("true");
        assertThat(headerFields.get("Connection").get(0)).as("Header - Connection").isEqualTo("keep-alive");
        assertThat(headerFields.get("Content-Length").get(0)).as("Header - Content-Length").isEqualTo("68");
        assertThat(headerFields.get("Content-Type").get(0)).as("Header - Content-Type").isEqualTo("application/json");

        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());
        String contentLength = (String) responseJson.get("Content-Length");
        String contentType = (String) responseJson.get("Content-Type");

        assertThat(contentLength).as("Body - Content-Length").isEqualTo("68");
        assertThat(contentType).as("Body - Content-Type").isEqualTo("application/json");
    }

    @Test(groups = "group2")
    public void testResponseHeadersInParameters1() {
        LOG.info("Test: testResponseHeadersInParameters1");

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl + "?Content-Type=text/plain;%20charset=UTF-8&Server=httpbin")
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("Content-Type").get(0)).as("Header - Content-Type").isEqualTo("text/plain; charset=UTF-8");
        assertThat(headerFields.get("Server").get(0)).as("Header - Server").isEqualTo("nginx");

        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());
        JSONArray jsonArray = responseJson.getJSONArray("Content-Type");
        String server = (String) responseJson.get("Server");

        assertThat(jsonArray.toString()).as("Body - contentType").isEqualTo("[\"application/json\",\"text/plain; charset=UTF-8\"]");
        assertThat(server).as("Body - server").isEqualTo("httpbin");
    }

    @Test(groups = "group2")
    public void testResponseHeadersInParameters2() {
        LOG.info("Test: testResponseHeadersInParameters2");

        Map<String, String> args = new HashMap<String, String>();
        args.put("hello1", "world1");

        String argsUrl = convertArgsToString(args);

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl + argsUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("hello1").get(0)).as("Header - hello1").isEqualTo("world1");

        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());
        String hello1 = (String) responseJson.get("hello1");

        assertThat(hello1).as("Body - hello1").isEqualTo(args.get("hello1"));
    }

    @Test(groups = "group2")
    public void testResponseHeadersInParameters3() {
        LOG.info("Test: testResponseHeadersInParameters3");

        Map<String, String> args = new HashMap<String, String>();
        args.put("hello1", "world1");
        args.put("hello2", "world2");

        String argsUrl = convertArgsToString(args);

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl + argsUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("hello1").get(0)).as("Header - hello1").isEqualTo("world1");
        assertThat(headerFields.get("hello2").get(0)).as("Header - hello2").isEqualTo("world2");

        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());

        String hello1 = null;
        String hello2 = null;
        try {
            hello1 = (String) responseJson.get("hello1");
            hello2 = (String) responseJson.get("hello2");
        } catch (JSONException e) {
            fail("!!!Bug was found!!! - Field isn't present in response body: " + e.getMessage());
        }

        assertThat(hello1).as("Body - hello1").isEqualTo(args.get("hello1"));
        assertThat(hello2).as("Body - hello2").isEqualTo(args.get("hello2"));
    }

    @Test(groups = "group2")
    public void testResponseHeadersInParameters4() {
        LOG.info("Test: testResponseHeadersInParameters4");

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl + "?hello1=world1&hello1=world2")
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("hello1").toString()).as("Header - hello1").isEqualTo("[world2, world1]");

        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());

        JSONArray jsonArray = responseJson.getJSONArray("hello1");

        assertThat(jsonArray.toString()).as("Body - hello1").isEqualTo("[\"world1\",\"world2\"]");
    }

    @Test(groups = "group2")
    public void testResponseHeadersInParameters5() {
        LOG.info("Test: testResponseHeadersInParameters5");

        Map<String, String> args = new HashMap<String, String>();
        args.put("hello1", "world12");
        args.put("hello2", "world12");

        String argsUrl = convertArgsToString(args);

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl + argsUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("hello1").get(0)).as("Header - hello1").isEqualTo("world12");
        assertThat(headerFields.get("hello2").get(0)).as("Header - hello2").isEqualTo("world12");

        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());

        String hello1 = (String) responseJson.get("hello1");
        String hello2 = (String) responseJson.get("hello2");

        assertThat(hello1).as("Body - hello1").isEqualTo(args.get("hello1"));
        assertThat(hello2).as("Body - hello2").isEqualTo(args.get("hello2"));
    }

    @Test(groups = "group1")
    public void testResponseHeadersInvalidMethod() {
        LOG.info("Test: testGetInvalidMethod");

        HttpResponse responseGet = HttpRequest
                .post(responseHeadersUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(405);
        assertThat(responseMessage).as("Response message").isEqualTo("METHOD NOT ALLOWED");
    }
}
