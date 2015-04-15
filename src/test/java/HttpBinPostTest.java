import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinPostTest {

    private static final String postUrl = "http://www.httpbin.org/post";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinPostTest.class.getSimpleName());

    @Test(groups = "group1")
    public void testPost() {
        LOG.info("Test: testPost");

        HttpResponse responsePost = HttpRequest
                .post(postUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .addBody("")
                .sendAndGetResponse();
    }
}
