import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinResponseHeadersTest {

    private static final String responseHeadersUrl = "http://www.httpbin.org/response-headers";
    private static Logger LOG = Logger.getLogger("HttpBinTest");

    @BeforeClass
    public void beforeClass(){
        LOG.info("Executing: " + this.getClass().getSimpleName());
    }

    @Test(groups = "group2")
    public void testResponseHeaders() {
        HttpResponse responseGet = HttpRequest
                .get(responseHeadersUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
