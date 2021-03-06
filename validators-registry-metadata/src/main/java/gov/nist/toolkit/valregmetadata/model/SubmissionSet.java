package gov.nist.toolkit.valregmetadata.model;

import gov.nist.toolkit.commondatatypes.MetadataSupport;
import gov.nist.toolkit.registrymetadata.Metadata;
import gov.nist.toolkit.xdsexception.client.XdsInternalException;
import org.apache.axiom.om.OMElement;

public class SubmissionSet extends AbstractRegistryObject implements TopLevelObject {


	public SubmissionSet(Metadata m, OMElement ro) throws XdsInternalException  {
		super(m, ro);
	}

	public SubmissionSet(String id) {
		super(id);
		internalClassifications.add(new InternalClassification("cl" + id, id, MetadataSupport.XDSSubmissionSet_classification_uuid));
	}

	public boolean equals(SubmissionSet s)  {
		if (!id.equals(id))
			return false;
		return	super.equals(s);
	}

	public String identifyingString() {
		return "SubmissionSet(" + getId() + ")";
	}


	public OMElement toXml() throws XdsInternalException  {
		ro = MetadataSupport.om_factory.createOMElement(MetadataSupport.registrypackage_qnamens);
		ro.addAttribute("id", id, null);
		if (status != null)
			ro.addAttribute("status", status, null);
		if (home != null)
			ro.addAttribute("home", home, null);

		addSlotsXml(ro);
		addNameToXml(ro);
		addDescriptionXml(ro);
		addClassificationsXml(ro);
		addAuthorsXml(ro);
		addExternalIdentifiersXml(ro);

		return ro;
	}

	public boolean isMetadataLimited() {
		return isClassifiedAs(MetadataSupport.XDSSubmissionSet_limitedMetadata_uuid);
	}



}
