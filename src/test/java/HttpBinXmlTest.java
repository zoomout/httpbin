import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;


public class HttpBinXmlTest extends HttpBinGeneral {

    private static final String xmlUrl = "http://www.httpbin.org/xml";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinXmlTest.class.getSimpleName());

    @Test(groups = "group2")
    public void testXmlDefault() {
        LOG.info("Test: testXmlDefault");

        HttpResponse responseGet = HttpRequest
                .get(xmlUrl)
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
        assertThat(headerFields.get("Content-Length").get(0)).as("Header - Content-Length").isEqualTo("522");
        assertThat(headerFields.get("Content-Type").get(0)).as("Header - Content-Type").isEqualTo("application/xml");
    }

    @Test(groups = "group2")
    public void testXmlBody() {
        LOG.info("Test: testXmlDefault");

        HttpResponse responseGet = HttpRequest
                .get(xmlUrl)
                .addHeader("Content-Type", "application/json")
                .sendAndGetResponse();

        String responseBody = responseGet.getResponseBody();
        String expectedBody = "<?xml version='1.0' encoding='us-ascii'?><!--  A SAMPLE set of slides  --><slideshow " +
                "    title=\"Sample Slide Show\"    date=\"Date of publication\"    author=\"Yours Truly\"    > " +
                "   <!-- TITLE SLIDE -->    <slide type=\"all\">      <title>Wake up to WonderWidgets!</title>  " +
                "  </slide>    <!-- OVERVIEW -->    <slide type=\"all\">        <title>Overview</title>   " +
                "     <item>Why <em>WonderWidgets</em> are great</item>        <item/>" +
                "        <item>Who <em>buys</em> WonderWidgets</item>    </slide></slideshow>";

        assertThat(responseBody).as("Response body").isEqualTo(expectedBody);
    }

    @Test(groups = "group1")
    public void testXmlInvalidMethod() {
        LOG.info("Test: testGetInvalidMethod");

        HttpResponse responseGet = HttpRequest
                .post(xmlUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(405);
        assertThat(responseMessage).as("Response message").isEqualTo("METHOD NOT ALLOWED");
    }
}
