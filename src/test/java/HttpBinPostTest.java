import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;


public class HttpBinPostTest extends HttpBinGeneral{

    private static final String postUrl = "http://www.httpbin.org/post";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinPostTest.class.getSimpleName());

    @Test(groups = "group1")
    public void testPostDefaultBodyResponse() {
        LOG.info("Test: testPostDefaultBodyResponse");

        HttpResponse responseGet = HttpRequest
                .post(postUrl)
                .addHeader("User-Agent", "Mozilla/5.0")
                .sendAndGetResponse();

        String requestMethod = responseGet.getRequestMethod();
        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        JSONObject responseJson = new JSONObject(responseGet.getResponseBody());
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(requestMethod).as("Request method").isEqualTo("POST");
        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("Server").get(0)).as("Header - Server").isEqualTo("nginx");
        assertThat(headerFields.get("Access-Control-Allow-Origin").get(0)).as("Header - Access-Control-Allow-Origin").isEqualTo("*");
        assertThat(headerFields.get("Access-Control-Allow-Credentials").get(0)).as("Header - Access-Control-Allow-Credentials").isEqualTo("true");
        assertThat(headerFields.get("Connection").get(0)).as("Header - Connection").isEqualTo("keep-alive");
        assertThat(headerFields.get("Content-Length").get(0)).as("Header - Content-Length").isEqualTo("309");
        assertThat(headerFields.get("Content-Type").get(0)).as("Header - Content-Type").isEqualTo("application/json");


        JSONObject responseJsonArgs = getJSONObjectArgs(responseJson);
        assertThat(responseJsonArgs).as("Response JSON Args").isNull();

        JSONObject responseJsonHeaders = getJSONObjectHeaders(responseJson);

        String accept = (String) responseJsonHeaders.get("Accept");
        String userAgent = (String) responseJsonHeaders.get("User-Agent");
        String host = (String) responseJsonHeaders.get("Host");

        assertThat(accept).as("Response body header Accept").isEqualTo("text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        assertThat(userAgent).as("Response body header User-Agent").isEqualTo("Mozilla/5.0");
        assertThat(host).as("Response body header Host").isEqualTo("www.httpbin.org");

        String data = (String) responseJson.get("data");
        assertThat(data).as("Response body data").isEqualTo("");

        JSONObject responseJsonForm = getJSONObjectForm(responseJson);
        assertThat(responseJsonForm).as("Response JSON Form").isNull();

        JSONObject responseJsonFiles = getJSONObjectFiles(responseJson);
        assertThat(responseJsonFiles).as("Response JSON Files").isNull();

        JSONObject responseJsonJson = getJSONObjectJson(responseJson);
        assertThat(responseJsonJson).as("Response JSON Json").isNull();

        String url = (String) responseJson.get("url");
        assertThat(url).as("Response body url").isEqualTo(postUrl);
    }

    @Test(groups = "group1")
    public void testPostJson1() {
        LOG.info("Test: testPostJson1");


        Map<String, String> jsonBodyContent = new HashMap<String, String>();
        jsonBodyContent.put("jsonKey1", "jsonValue1");
        jsonBodyContent.put("jsonKey2", "jsonValue2");
        JSONObject requestJsonBody = new JSONObject(new Gson().toJson(jsonBodyContent));

        HttpResponse responsePost = HttpRequest
                .post(postUrl)
                .addHeader("Content-Type", HttpRequest.CONTENT_TYPE.APPLICATION_JSON.toString())
                .addBody(requestJsonBody.toString())
                .sendAndGetResponse();

        JSONObject responseJson = new JSONObject(responsePost.getResponseBody());

        JSONObject responseJsonJson = getJSONObjectJson(responseJson);
        assertThat(responseJsonJson.toString()).as("Json in response body").isEqualTo(requestJsonBody.toString());

        String data = (String) responseJson.get("data");
        assertThat(data).as("Data in response body").isEqualTo(requestJsonBody.toString());
    }

    @Test(groups = "group1")
    public void testPostJson2() {
        LOG.info("Test: testPostJson2");


        Map<String, String> jsonBodyContent = new HashMap<String, String>();
        jsonBodyContent.put("jsonKey1", "jsonValue1");
        jsonBodyContent.put("jsonKey2", "jsonValue1");
        JSONObject requestJsonBody = new JSONObject(new Gson().toJson(jsonBodyContent));

        HttpResponse responsePost = HttpRequest
                .post(postUrl)
                .addHeader("Content-Type", HttpRequest.CONTENT_TYPE.APPLICATION_JSON.toString())
                .addBody(requestJsonBody.toString())
                .sendAndGetResponse();

        JSONObject responseJson = new JSONObject(responsePost.getResponseBody());

        JSONObject responseJsonJson = getJSONObjectJson(responseJson);
        assertThat(responseJsonJson.toString()).as("Json in response body").isEqualTo(requestJsonBody.toString());

        String data = (String) responseJson.get("data");
        assertThat(data).as("Data in response body").isEqualTo(requestJsonBody.toString());
    }

    @Test(groups = "group1")
    public void testPostJson3() {
        LOG.info("Test: testPostJson3");

        HttpResponse responsePost = HttpRequest
                .post(postUrl)
                .addHeader("Content-Type", HttpRequest.CONTENT_TYPE.APPLICATION_JSON.toString())
                .addBody("{\"jsonKey1\":\"jsonValue2\",\n" +
                        "  \"jsonKey1\":\"jsonValue1\"}")
                .sendAndGetResponse();

        JSONObject responseJson = new JSONObject(responsePost.getResponseBody());

        JSONObject responseJsonJson = getJSONObjectJson(responseJson);
        assertThat(responseJsonJson.toString()).as("Json in response body").isEqualTo("{\"jsonKey1\":\"jsonValue1\"}");

        String data = (String) responseJson.get("data");
        assertThat(data).as("Data in response body").isEqualTo("{\"jsonKey1\":\"jsonValue2\",\n" +
                "  \"jsonKey1\":\"jsonValue1\"}");
    }

    @Test(groups = "group1")
    public void testPostArgs() {
        LOG.info("Test: testPostArgs");

        Map<String, String> args = new HashMap<String, String>();
        args.put("hello1", "world1");
        args.put("hello2", "world2");

        String argsUrl = convertArgsToString(args);

        HttpResponse responsePost = HttpRequest
                .post(postUrl + argsUrl)
                .sendAndGetResponse();

        JSONObject responseJsonArgs = getJSONObjectArgs(new JSONObject(responsePost.getResponseBody()));

        JSONObject expectedJsonArgs = new JSONObject(new Gson().toJson(args));

        assertThat(responseJsonArgs.toString()).as("JSON body args").isEqualTo(expectedJsonArgs.toString());
    }

    @Test(groups = "group1")
    public void testPostInvalidMethod() {
        LOG.info("Test: testPostInvalidMethod");

        HttpResponse responseGet = HttpRequest
                .get(postUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(405);
        assertThat(responseMessage).as("Response message").isEqualTo("METHOD NOT ALLOWED");
    }
}
