
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

    private final static QName _GetTopoXML_QNAME = new QName("http://server.stat.gossip/", "getTopoXML");
    private final static QName _GetXML_QNAME = new QName("http://server.stat.gossip/", "getXML");
    private final static QName _GetNodeResponse_QNAME = new QName("http://server.stat.gossip/", "getNodeResponse");
    private final static QName _SendTopology_QNAME = new QName("http://server.stat.gossip/", "sendTopology");
    private final static QName _GetXMLResponse_QNAME = new QName("http://server.stat.gossip/", "getXMLResponse");
    private final static QName _Leave_QNAME = new QName("http://server.stat.gossip/", "leave");
    private final static QName _SendList_QNAME = new QName("http://server.stat.gossip/", "sendList");
    private final static QName _GetTopoNodeResponse_QNAME = new QName("http://server.stat.gossip/", "getTopoNodeResponse");
    private final static QName _SendListResponse_QNAME = new QName("http://server.stat.gossip/", "sendListResponse");
    private final static QName _GetTopoNode_QNAME = new QName("http://server.stat.gossip/", "getTopoNode");
    private final static QName _SendTopologyResponse_QNAME = new QName("http://server.stat.gossip/", "sendTopologyResponse");
    private final static QName _GetTopoXMLResponse_QNAME = new QName("http://server.stat.gossip/", "getTopoXMLResponse");
    private final static QName _LeaveResponse_QNAME = new QName("http://server.stat.gossip/", "leaveResponse");
    private final static QName _GetNode_QNAME = new QName("http://server.stat.gossip/", "getNode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gossip.stat.client.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTopoNode }
     * 
     */
    public GetTopoNode createGetTopoNode() {
        return new GetTopoNode();
    }

    /**
     * Create an instance of {@link SendListResponse }
     * 
     */
    public SendListResponse createSendListResponse() {
        return new SendListResponse();
    }

    /**
     * Create an instance of {@link SendTopologyResponse }
     * 
     */
    public SendTopologyResponse createSendTopologyResponse() {
        return new SendTopologyResponse();
    }

    /**
     * Create an instance of {@link GetTopoXMLResponse }
     * 
     */
    public GetTopoXMLResponse createGetTopoXMLResponse() {
        return new GetTopoXMLResponse();
    }

    /**
     * Create an instance of {@link LeaveResponse }
     * 
     */
    public LeaveResponse createLeaveResponse() {
        return new LeaveResponse();
    }

    /**
     * Create an instance of {@link GetNode }
     * 
     */
    public GetNode createGetNode() {
        return new GetNode();
    }

    /**
     * Create an instance of {@link GetTopoXML }
     * 
     */
    public GetTopoXML createGetTopoXML() {
        return new GetTopoXML();
    }

    /**
     * Create an instance of {@link GetXML }
     * 
     */
    public GetXML createGetXML() {
        return new GetXML();
    }

    /**
     * Create an instance of {@link SendTopology }
     * 
     */
    public SendTopology createSendTopology() {
        return new SendTopology();
    }

    /**
     * Create an instance of {@link GetXMLResponse }
     * 
     */
    public GetXMLResponse createGetXMLResponse() {
        return new GetXMLResponse();
    }

    /**
     * Create an instance of {@link Leave }
     * 
     */
    public Leave createLeave() {
        return new Leave();
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
     * Create an instance of {@link GetTopoNodeResponse }
     * 
     */
    public GetTopoNodeResponse createGetTopoNodeResponse() {
        return new GetTopoNodeResponse();
    }

    /**
     * Create an instance of {@link Node }
     * 
     */
    public Node createNode() {
        return new Node();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTopoXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getTopoXML")
    public JAXBElement<GetTopoXML> createGetTopoXML(GetTopoXML value) {
        return new JAXBElement<GetTopoXML>(_GetTopoXML_QNAME, GetTopoXML.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTopology }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "sendTopology")
    public JAXBElement<SendTopology> createSendTopology(SendTopology value) {
        return new JAXBElement<SendTopology>(_SendTopology_QNAME, SendTopology.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Leave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "leave")
    public JAXBElement<Leave> createLeave(Leave value) {
        return new JAXBElement<Leave>(_Leave_QNAME, Leave.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTopoNodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getTopoNodeResponse")
    public JAXBElement<GetTopoNodeResponse> createGetTopoNodeResponse(GetTopoNodeResponse value) {
        return new JAXBElement<GetTopoNodeResponse>(_GetTopoNodeResponse_QNAME, GetTopoNodeResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTopoNode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getTopoNode")
    public JAXBElement<GetTopoNode> createGetTopoNode(GetTopoNode value) {
        return new JAXBElement<GetTopoNode>(_GetTopoNode_QNAME, GetTopoNode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTopologyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "sendTopologyResponse")
    public JAXBElement<SendTopologyResponse> createSendTopologyResponse(SendTopologyResponse value) {
        return new JAXBElement<SendTopologyResponse>(_SendTopologyResponse_QNAME, SendTopologyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTopoXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getTopoXMLResponse")
    public JAXBElement<GetTopoXMLResponse> createGetTopoXMLResponse(GetTopoXMLResponse value) {
        return new JAXBElement<GetTopoXMLResponse>(_GetTopoXMLResponse_QNAME, GetTopoXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "leaveResponse")
    public JAXBElement<LeaveResponse> createLeaveResponse(LeaveResponse value) {
        return new JAXBElement<LeaveResponse>(_LeaveResponse_QNAME, LeaveResponse.class, null, value);
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
