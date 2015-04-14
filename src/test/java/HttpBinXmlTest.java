import org.testng.annotations.Test;

import java.util.logging.Logger;


public class HttpBinXmlTest {

    private static final String xmlUrl = "http://www.httpbin.org/xml";
    private static Logger LOG = Logger.getLogger("HttpBinTest");

    @Test(groups = "group2")
    public void testXml() {
        LOG.info("Executing testXml");
        HttpResponse responseGet = HttpRequest
                .get(xmlUrl)
                .addHeader("User-Agent", Common.USER_AGENT)
                .sendAndGetResponse();
    }
}
