package gov.nist.toolkit.valregmsg.registry;

import gov.nist.toolkit.commondatatypes.MetadataSupport;
import gov.nist.toolkit.registrymsg.registry.Response;
import gov.nist.toolkit.registrymsg.repository.RetrievedDocumentModel;
import gov.nist.toolkit.registrysupport.RegistryErrorListGenerator;
import gov.nist.toolkit.xdsexception.client.XdsInternalException;
import org.apache.axiom.om.OMElement;

public class RetrieveMultipleResponse extends Response {
	OMElement rdsr = null;

	@Override
   public OMElement getRoot() { return rdsr; }

	public RetrieveMultipleResponse() throws XdsInternalException {
		super();
		response = MetadataSupport.om_factory.createOMElement("RegistryResponse", ebRSns);
		rdsr = MetadataSupport.om_factory.createOMElement("RetrieveDocumentSetResponse", MetadataSupport.xdsB);
		rdsr.addChild(response);
	}

	public RetrieveMultipleResponse(RegistryErrorListGenerator rel) throws XdsInternalException {
		super(rel);
		response = MetadataSupport.om_factory.createOMElement("RegistryResponse", ebRSns);
		rdsr = MetadataSupport.om_factory.createOMElement("RetrieveDocumentSetResponse", MetadataSupport.xdsB);
		rdsr.addChild(response);
	}

    @Override
	public void addQueryResults(OMElement metadata) { }

    public void add(RetrievedDocumentModel model) {

    }


}
