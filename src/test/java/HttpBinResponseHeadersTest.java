import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinResponseHeadersTest {

    private static final String responseHeadersUrl = "http://www.httpbin.org/response-headers";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinResponseHeadersTest.class.getSimpleName());

    @Test(groups = "group2")
    public void testResponseHeaders() {
        LOG.info("Test: testResponseHeaders");

        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
