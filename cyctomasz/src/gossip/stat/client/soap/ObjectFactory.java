
package gossip.stat.client.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gossip.stat.client.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetXML_QNAME = new QName("http://server.stat.gossip/", "getXML");
    private final static QName _GetNodeResponse_QNAME = new QName("http://server.stat.gossip/", "getNodeResponse");
    private final static QName _SendListResponse_QNAME = new QName("http://server.stat.gossip/", "sendListResponse");
    private final static QName _GetXMLResponse_QNAME = new QName("http://server.stat.gossip/", "getXMLResponse");
    private final static QName _SendList_QNAME = new QName("http://server.stat.gossip/", "sendList");
    private final static QName _GetNode_QNAME = new QName("http://server.stat.gossip/", "getNode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gossip.stat.client.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetXML }
     * 
     */
    public GetXML createGetXML() {
        return new GetXML();
    }

    /**
     * Create an instance of {@link GetXMLResponse }
     * 
     */
    public GetXMLResponse createGetXMLResponse() {
        return new GetXMLResponse();
    }

    /**
     * Create an instance of {@link SendListResponse }
     * 
     */
    public SendListResponse createSendListResponse() {
        return new SendListResponse();
    }

    /**
     * Create an instance of {@link GetNodeResponse }
     * 
     */
    public GetNodeResponse createGetNodeResponse() {
        return new GetNodeResponse();
    }

    /**
     * Create an instance of {@link SendList }
     * 
     */
    public SendList createSendList() {
        return new SendList();
    }

    /**
     * Create an instance of {@link GetNode }
     * 
     */
    public GetNode createGetNode() {
        return new GetNode();
    }

    /**
     * Create an instance of {@link Node }
     * 
     */
    public Node createNode() {
        return new Node();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getXML")
    public JAXBElement<GetXML> createGetXML(GetXML value) {
        return new JAXBElement<GetXML>(_GetXML_QNAME, GetXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getNodeResponse")
    public JAXBElement<GetNodeResponse> createGetNodeResponse(GetNodeResponse value) {
        return new JAXBElement<GetNodeResponse>(_GetNodeResponse_QNAME, GetNodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "sendListResponse")
    public JAXBElement<SendListResponse> createSendListResponse(SendListResponse value) {
        return new JAXBElement<SendListResponse>(_SendListResponse_QNAME, SendListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getXMLResponse")
    public JAXBElement<GetXMLResponse> createGetXMLResponse(GetXMLResponse value) {
        return new JAXBElement<GetXMLResponse>(_GetXMLResponse_QNAME, GetXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "sendList")
    public JAXBElement<SendList> createSendList(SendList value) {
        return new JAXBElement<SendList>(_SendList_QNAME, SendList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getNode")
    public JAXBElement<GetNode> createGetNode(GetNode value) {
        return new JAXBElement<GetNode>(_GetNode_QNAME, GetNode.class, null, value);
    }

}
