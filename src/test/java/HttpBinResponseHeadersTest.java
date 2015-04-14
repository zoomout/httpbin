import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinResponseHeadersTest {

    private static final String responseHeadersUrl = "http://www.httpbin.org/response-headers";
    private static Logger LOG = Logger.getLogger("HttpBinTest");

    @Test(groups = "group2")
    public void testResponseHeaders() {
        LOG.info("Executing testResponseHeaders");
        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
