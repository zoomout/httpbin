import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinXmlTest {

    private static final String xmlUrl = "http://www.httpbin.org/xml";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinXmlTest.class.getSimpleName());

    @Test(groups = "group2")
    public void testXml() {
        LOG.info("Test: testXml");

        HttpResponse responseGet = HttpRequest
                .get(xmlUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
