import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinGetTest {

    private static final String getUrl = "http://www.httpbin.org/get";
    private static Logger LOG = Logger.getLogger("HttpBinGetTest");

    @BeforeClass
    public void beforeClass(){
        LOG.info("Executing: " + this.getClass().getSimpleName());
    }

    @Test(groups = "group1")
    public void testGet() {
        HttpResponse responseGet = HttpRequest
                .get(getUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
