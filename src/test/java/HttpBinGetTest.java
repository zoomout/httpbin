import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinGetTest {

    private static final String getUrl = "http://www.httpbin.org/get";
    private static Logger LOG = Logger.getLogger("HttpBinGetTest");

    @Test(groups = "group1")
    public void testGet() {
        LOG.info("Executing testGet");
        HttpResponse responseGet = HttpRequest
                .get(getUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
        System.out.println();
    }
}
