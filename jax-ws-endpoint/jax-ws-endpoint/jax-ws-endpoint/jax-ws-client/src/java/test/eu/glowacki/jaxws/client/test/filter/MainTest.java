package eu.glowacki.jaxws.client.test.filter;

import eu.glowacki.jaxws.api.IService;
import eu.glowacki.jaxws.api.composite.AddRequest;
import eu.glowacki.jaxws.api.composite.AddResponse;
import eu.glowacki.jaxws.api.composite.IComposite;
import eu.glowacki.jaxws.api.filter.FilterRequest;
import eu.glowacki.jaxws.api.filter.FilterResponse;
import eu.glowacki.jaxws.api.filter.IFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class MainTest {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    public	static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    static {
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "9999999");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dumpTreshold", "9999999");
    }

    private IFilter filter;

    @Before
    public void before() throws MalformedURLException {
        URL wsdl = new URL(IFilter.URI + IService.WSDL_SUFFIX);
        QName qname = new QName("http://glowacki.eu/filter", "");
        Service service = Service.create(wsdl, qname);
        filter = service.getPort(IFilter.class);
    }

    @Test
    public void filter() throws ParseException {
        FilterRequest request = new FilterRequest("Kotiun", formatter.parse("13-04-2002"));
        FilterResponse response = filter.search(request);

        LOGGER.info("response: " + response.toString());
        int size = response.getPersonSet().size();
        Assert.assertEquals(2, size);
    }
}
