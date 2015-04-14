import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinXmlTest {

    private static final String xmlUrl = "http://www.httpbin.org/xml";
    private static Logger LOG = Logger.getLogger("HttpBinTest");

    @BeforeClass
    public void beforeClass(){
        LOG.info("Executing: " + this.getClass().getSimpleName());
    }

    @Test(groups = "group2")
    public void testXml() {
        HttpResponse responseGet = HttpRequest
                .get(xmlUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
