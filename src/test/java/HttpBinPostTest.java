import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinPostTest {

    private static final String postUrl = "http://www.httpbin.org/post";
    private static Logger LOG = Logger.getLogger("HttpBinTest");

    @Test(groups = "group1")
    public void testPost() {
        LOG.info("Executing testPost");
        HttpResponse responsePost = HttpRequest
                .post(postUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .addBody("")
                .sendAndGetResponse();
    }
}
