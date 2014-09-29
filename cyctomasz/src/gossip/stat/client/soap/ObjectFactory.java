
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
    private final static QName _WriteResults_QNAME = new QName("http://server.stat.gossip/", "writeResults");
    private final static QName _GetNodeNumber_QNAME = new QName("http://server.stat.gossip/", "getNodeNumber");
    private final static QName _Leave_QNAME = new QName("http://server.stat.gossip/", "leave");
    private final static QName _CleanupResponse_QNAME = new QName("http://server.stat.gossip/", "cleanupResponse");
    private final static QName _GetCounter_QNAME = new QName("http://server.stat.gossip/", "getCounter");
    private final static QName _Cleanup_QNAME = new QName("http://server.stat.gossip/", "cleanup");
    private final static QName _SendListResponse_QNAME = new QName("http://server.stat.gossip/", "sendListResponse");
    private final static QName _GetCounterResponse_QNAME = new QName("http://server.stat.gossip/", "getCounterResponse");
    private final static QName _WriteTopoXML_QNAME = new QName("http://server.stat.gossip/", "writeTopoXML");
    private final static QName _WriteAlternativeXML_QNAME = new QName("http://server.stat.gossip/", "writeAlternativeXML");
    private final static QName _GetTopoXMLResponse_QNAME = new QName("http://server.stat.gossip/", "getTopoXMLResponse");
    private final static QName _GetNodeNumberResponse_QNAME = new QName("http://server.stat.gossip/", "getNodeNumberResponse");
    private final static QName _GetWaitingMessagesResponse_QNAME = new QName("http://server.stat.gossip/", "getWaitingMessagesResponse");
    private final static QName _WriteResultsResponse_QNAME = new QName("http://server.stat.gossip/", "writeResultsResponse");
    private final static QName _GetTopoXML_QNAME = new QName("http://server.stat.gossip/", "getTopoXML");
    private final static QName _GetLostPackagesCounter_QNAME = new QName("http://server.stat.gossip/", "getLostPackagesCounter");
    private final static QName _SendTopology_QNAME = new QName("http://server.stat.gossip/", "sendTopology");
    private final static QName _GetXMLResponse_QNAME = new QName("http://server.stat.gossip/", "getXMLResponse");
    private final static QName _SendList2Response_QNAME = new QName("http://server.stat.gossip/", "sendList2Response");
    private final static QName _SendList_QNAME = new QName("http://server.stat.gossip/", "sendList");
    private final static QName _GetWaitingMessages_QNAME = new QName("http://server.stat.gossip/", "getWaitingMessages");
    private final static QName _SendList2_QNAME = new QName("http://server.stat.gossip/", "sendList2");
    private final static QName _WriteTopoXMLResponse_QNAME = new QName("http://server.stat.gossip/", "writeTopoXMLResponse");
    private final static QName _WriteXMLResponse_QNAME = new QName("http://server.stat.gossip/", "writeXMLResponse");
    private final static QName _SendTopologyResponse_QNAME = new QName("http://server.stat.gossip/", "sendTopologyResponse");
    private final static QName _WriteXML_QNAME = new QName("http://server.stat.gossip/", "writeXML");
    private final static QName _LeaveResponse_QNAME = new QName("http://server.stat.gossip/", "leaveResponse");
    private final static QName _WriteAlternativeXMLResponse_QNAME = new QName("http://server.stat.gossip/", "writeAlternativeXMLResponse");
    private final static QName _GetLostPackagesCounterResponse_QNAME = new QName("http://server.stat.gossip/", "getLostPackagesCounterResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gossip.stat.client.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cleanup }
     * 
     */
    public Cleanup createCleanup() {
        return new Cleanup();
    }

    /**
     * Create an instance of {@link SendListResponse }
     * 
     */
    public SendListResponse createSendListResponse() {
        return new SendListResponse();
    }

    /**
     * Create an instance of {@link WriteTopoXML }
     * 
     */
    public WriteTopoXML createWriteTopoXML() {
        return new WriteTopoXML();
    }

    /**
     * Create an instance of {@link GetCounterResponse }
     * 
     */
    public GetCounterResponse createGetCounterResponse() {
        return new GetCounterResponse();
    }

    /**
     * Create an instance of {@link GetTopoXMLResponse }
     * 
     */
    public GetTopoXMLResponse createGetTopoXMLResponse() {
        return new GetTopoXMLResponse();
    }

    /**
     * Create an instance of {@link WriteAlternativeXML }
     * 
     */
    public WriteAlternativeXML createWriteAlternativeXML() {
        return new WriteAlternativeXML();
    }

    /**
     * Create an instance of {@link GetNodeNumberResponse }
     * 
     */
    public GetNodeNumberResponse createGetNodeNumberResponse() {
        return new GetNodeNumberResponse();
    }

    /**
     * Create an instance of {@link WriteResults }
     * 
     */
    public WriteResults createWriteResults() {
        return new WriteResults();
    }

    /**
     * Create an instance of {@link GetXML }
     * 
     */
    public GetXML createGetXML() {
        return new GetXML();
    }

    /**
     * Create an instance of {@link Leave }
     * 
     */
    public Leave createLeave() {
        return new Leave();
    }

    /**
     * Create an instance of {@link GetNodeNumber }
     * 
     */
    public GetNodeNumber createGetNodeNumber() {
        return new GetNodeNumber();
    }

    /**
     * Create an instance of {@link GetCounter }
     * 
     */
    public GetCounter createGetCounter() {
        return new GetCounter();
    }

    /**
     * Create an instance of {@link CleanupResponse }
     * 
     */
    public CleanupResponse createCleanupResponse() {
        return new CleanupResponse();
    }

    /**
     * Create an instance of {@link WriteXMLResponse }
     * 
     */
    public WriteXMLResponse createWriteXMLResponse() {
        return new WriteXMLResponse();
    }

    /**
     * Create an instance of {@link WriteTopoXMLResponse }
     * 
     */
    public WriteTopoXMLResponse createWriteTopoXMLResponse() {
        return new WriteTopoXMLResponse();
    }

    /**
     * Create an instance of {@link SendTopologyResponse }
     * 
     */
    public SendTopologyResponse createSendTopologyResponse() {
        return new SendTopologyResponse();
    }

    /**
     * Create an instance of {@link WriteXML }
     * 
     */
    public WriteXML createWriteXML() {
        return new WriteXML();
    }

    /**
     * Create an instance of {@link LeaveResponse }
     * 
     */
    public LeaveResponse createLeaveResponse() {
        return new LeaveResponse();
    }

    /**
     * Create an instance of {@link GetLostPackagesCounterResponse }
     * 
     */
    public GetLostPackagesCounterResponse createGetLostPackagesCounterResponse() {
        return new GetLostPackagesCounterResponse();
    }

    /**
     * Create an instance of {@link WriteAlternativeXMLResponse }
     * 
     */
    public WriteAlternativeXMLResponse createWriteAlternativeXMLResponse() {
        return new WriteAlternativeXMLResponse();
    }

    /**
     * Create an instance of {@link WriteResultsResponse }
     * 
     */
    public WriteResultsResponse createWriteResultsResponse() {
        return new WriteResultsResponse();
    }

    /**
     * Create an instance of {@link GetTopoXML }
     * 
     */
    public GetTopoXML createGetTopoXML() {
        return new GetTopoXML();
    }

    /**
     * Create an instance of {@link GetWaitingMessagesResponse }
     * 
     */
    public GetWaitingMessagesResponse createGetWaitingMessagesResponse() {
        return new GetWaitingMessagesResponse();
    }

    /**
     * Create an instance of {@link GetLostPackagesCounter }
     * 
     */
    public GetLostPackagesCounter createGetLostPackagesCounter() {
        return new GetLostPackagesCounter();
    }

    /**
     * Create an instance of {@link GetXMLResponse }
     * 
     */
    public GetXMLResponse createGetXMLResponse() {
        return new GetXMLResponse();
    }

    /**
     * Create an instance of {@link SendTopology }
     * 
     */
    public SendTopology createSendTopology() {
        return new SendTopology();
    }

    /**
     * Create an instance of {@link GetWaitingMessages }
     * 
     */
    public GetWaitingMessages createGetWaitingMessages() {
        return new GetWaitingMessages();
    }

    /**
     * Create an instance of {@link SendList }
     * 
     */
    public SendList createSendList() {
        return new SendList();
    }

    /**
     * Create an instance of {@link SendList2Response }
     * 
     */
    public SendList2Response createSendList2Response() {
        return new SendList2Response();
    }

    /**
     * Create an instance of {@link SendList2 }
     * 
     */
    public SendList2 createSendList2() {
        return new SendList2();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeResults")
    public JAXBElement<WriteResults> createWriteResults(WriteResults value) {
        return new JAXBElement<WriteResults>(_WriteResults_QNAME, WriteResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNodeNumber }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getNodeNumber")
    public JAXBElement<GetNodeNumber> createGetNodeNumber(GetNodeNumber value) {
        return new JAXBElement<GetNodeNumber>(_GetNodeNumber_QNAME, GetNodeNumber.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CleanupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "cleanupResponse")
    public JAXBElement<CleanupResponse> createCleanupResponse(CleanupResponse value) {
        return new JAXBElement<CleanupResponse>(_CleanupResponse_QNAME, CleanupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCounter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getCounter")
    public JAXBElement<GetCounter> createGetCounter(GetCounter value) {
        return new JAXBElement<GetCounter>(_GetCounter_QNAME, GetCounter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cleanup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "cleanup")
    public JAXBElement<Cleanup> createCleanup(Cleanup value) {
        return new JAXBElement<Cleanup>(_Cleanup_QNAME, Cleanup.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCounterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getCounterResponse")
    public JAXBElement<GetCounterResponse> createGetCounterResponse(GetCounterResponse value) {
        return new JAXBElement<GetCounterResponse>(_GetCounterResponse_QNAME, GetCounterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteTopoXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeTopoXML")
    public JAXBElement<WriteTopoXML> createWriteTopoXML(WriteTopoXML value) {
        return new JAXBElement<WriteTopoXML>(_WriteTopoXML_QNAME, WriteTopoXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteAlternativeXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeAlternativeXML")
    public JAXBElement<WriteAlternativeXML> createWriteAlternativeXML(WriteAlternativeXML value) {
        return new JAXBElement<WriteAlternativeXML>(_WriteAlternativeXML_QNAME, WriteAlternativeXML.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNodeNumberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getNodeNumberResponse")
    public JAXBElement<GetNodeNumberResponse> createGetNodeNumberResponse(GetNodeNumberResponse value) {
        return new JAXBElement<GetNodeNumberResponse>(_GetNodeNumberResponse_QNAME, GetNodeNumberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitingMessagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getWaitingMessagesResponse")
    public JAXBElement<GetWaitingMessagesResponse> createGetWaitingMessagesResponse(GetWaitingMessagesResponse value) {
        return new JAXBElement<GetWaitingMessagesResponse>(_GetWaitingMessagesResponse_QNAME, GetWaitingMessagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteResultsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeResultsResponse")
    public JAXBElement<WriteResultsResponse> createWriteResultsResponse(WriteResultsResponse value) {
        return new JAXBElement<WriteResultsResponse>(_WriteResultsResponse_QNAME, WriteResultsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLostPackagesCounter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getLostPackagesCounter")
    public JAXBElement<GetLostPackagesCounter> createGetLostPackagesCounter(GetLostPackagesCounter value) {
        return new JAXBElement<GetLostPackagesCounter>(_GetLostPackagesCounter_QNAME, GetLostPackagesCounter.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SendList2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "sendList2Response")
    public JAXBElement<SendList2Response> createSendList2Response(SendList2Response value) {
        return new JAXBElement<SendList2Response>(_SendList2Response_QNAME, SendList2Response.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitingMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getWaitingMessages")
    public JAXBElement<GetWaitingMessages> createGetWaitingMessages(GetWaitingMessages value) {
        return new JAXBElement<GetWaitingMessages>(_GetWaitingMessages_QNAME, GetWaitingMessages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendList2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "sendList2")
    public JAXBElement<SendList2> createSendList2(SendList2 value) {
        return new JAXBElement<SendList2>(_SendList2_QNAME, SendList2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteTopoXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeTopoXMLResponse")
    public JAXBElement<WriteTopoXMLResponse> createWriteTopoXMLResponse(WriteTopoXMLResponse value) {
        return new JAXBElement<WriteTopoXMLResponse>(_WriteTopoXMLResponse_QNAME, WriteTopoXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeXMLResponse")
    public JAXBElement<WriteXMLResponse> createWriteXMLResponse(WriteXMLResponse value) {
        return new JAXBElement<WriteXMLResponse>(_WriteXMLResponse_QNAME, WriteXMLResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeXML")
    public JAXBElement<WriteXML> createWriteXML(WriteXML value) {
        return new JAXBElement<WriteXML>(_WriteXML_QNAME, WriteXML.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteAlternativeXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "writeAlternativeXMLResponse")
    public JAXBElement<WriteAlternativeXMLResponse> createWriteAlternativeXMLResponse(WriteAlternativeXMLResponse value) {
        return new JAXBElement<WriteAlternativeXMLResponse>(_WriteAlternativeXMLResponse_QNAME, WriteAlternativeXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLostPackagesCounterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.stat.gossip/", name = "getLostPackagesCounterResponse")
    public JAXBElement<GetLostPackagesCounterResponse> createGetLostPackagesCounterResponse(GetLostPackagesCounterResponse value) {
        return new JAXBElement<GetLostPackagesCounterResponse>(_GetLostPackagesCounterResponse_QNAME, GetLostPackagesCounterResponse.class, null, value);
    }

}
