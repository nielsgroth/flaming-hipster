
package gossip.stat.client.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "StatServerService", targetNamespace = "http://server.stat.gossip/", wsdlLocation = "http://87.77.4.63:8000/gossip?wsdl")
public class StatServerService
    extends Service
{

    private final static URL STATSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException STATSERVERSERVICE_EXCEPTION;
    private final static QName STATSERVERSERVICE_QNAME = new QName("http://server.stat.gossip/", "StatServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://87.77.4.63:8000/gossip?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STATSERVERSERVICE_WSDL_LOCATION = url;
        STATSERVERSERVICE_EXCEPTION = e;
    }

    public StatServerService() {
        super(__getWsdlLocation(), STATSERVERSERVICE_QNAME);
    }

    public StatServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), STATSERVERSERVICE_QNAME, features);
    }

    public StatServerService(URL wsdlLocation) {
        super(wsdlLocation, STATSERVERSERVICE_QNAME);
    }

    public StatServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, STATSERVERSERVICE_QNAME, features);
    }

    public StatServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StatServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns StatServer
     */
    @WebEndpoint(name = "StatServerPort")
    public StatServer getStatServerPort() {
        return super.getPort(new QName("http://server.stat.gossip/", "StatServerPort"), StatServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StatServer
     */
    @WebEndpoint(name = "StatServerPort")
    public StatServer getStatServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.stat.gossip/", "StatServerPort"), StatServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STATSERVERSERVICE_EXCEPTION!= null) {
            throw STATSERVERSERVICE_EXCEPTION;
        }
        return STATSERVERSERVICE_WSDL_LOCATION;
    }

}
