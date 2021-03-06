package gov.nist.toolkit.xdstools2.client.tabs.conformanceTest;

import com.google.gwt.user.client.rpc.AsyncCallback;
import gov.nist.toolkit.actortransaction.client.ActorType;
import gov.nist.toolkit.results.client.TestInstance;
import gov.nist.toolkit.xdstools2.client.command.command.GetCollectionMembersCommand;
import gov.nist.toolkit.xdstools2.client.tabs.GatewayTestsTabs.BuildRGTestOrchestrationButton;
import gov.nist.toolkit.xdstools2.client.util.ClientUtils;
import gov.nist.toolkit.xdstools2.shared.command.request.GetCollectionRequest;

import java.util.List;

/**
 * A type that takes into account both the actor type and option selected
 */
public class ActorOption {
    String actorTypeId;
    String optionId;

    public ActorOption(String actorTypeId) {
        this.actorTypeId = actorTypeId;
        optionId = "";
    }

    public ActorOption(String actorTypeId, String optionId) {
        this.actorTypeId = actorTypeId;
        this.optionId = optionId;
    }

    /**
     * Tests for options are listed in collections as actorType_optionName
     * @param callback with list of testIds
     */
    void loadTests(final AsyncCallback<List<TestInstance>> callback) {
        GetCollectionRequest request;
        if (optionId == null || optionId.equals("")) {
            request = new GetCollectionRequest(ClientUtils.INSTANCE.getCommandContext(), "actorcollections", actorTypeId);
        } else {
            request = new GetCollectionRequest(ClientUtils.INSTANCE.getCommandContext(), "collections", actorTypeId + "_" + optionId);
        }
        new GetCollectionMembersCommand() {
            @Override
            public void onComplete(List<TestInstance> result) {
                callback.onSuccess(result);
            }
        }.run(request);
    }

    public boolean isRep() {
        return actorTypeId != null && ActorType.REPOSITORY.getShortName().equals(actorTypeId);
    }

    public boolean isRg() {
        return actorTypeId != null && ActorType.RESPONDING_GATEWAY.getShortName().equals(actorTypeId);
    }

    public boolean isIg() {
        return actorTypeId != null && ActorType.INITIATING_GATEWAY.getShortName().equals(actorTypeId);
    }

    public boolean isReg() {
        return actorTypeId != null && ActorType.REGISTRY.getShortName().equals(actorTypeId);
    }

    public boolean isRec() {
        return actorTypeId != null && ActorType.DOCUMENT_RECIPIENT.getShortName().equals(actorTypeId);
    }

    public boolean isInitiatingImagingGatewaySut() {
        return actorTypeId != null
                && ActorType.INITIATING_IMAGING_GATEWAY.getShortName().equals(actorTypeId);
    }

    public boolean isRespondingingImagingGatewaySut() {
        return actorTypeId != null
                && ActorType.RESPONDING_IMAGING_GATEWAY.getShortName().equals(actorTypeId);
    }

    public boolean isEdgeServerSut() {
        return false;
    }

    public boolean isOnDemand() {
        return optionId.equals(BuildRGTestOrchestrationButton.ON_DEMAND_OPTION);
    }

    public boolean isImagingDocSourceSut() {
        return actorTypeId != null
                && ActorType.IMAGING_DOC_SOURCE.getShortName().equals(actorTypeId);
    }
    
    public boolean isIDC() {
       return actorTypeId != null 
                && ActorType.IMAGING_DOC_CONSUMER.getShortName().equals(actorTypeId);
    }

    void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getActorTypeId() {
        return actorTypeId;
    }

    public String getOptionId() {
        return optionId;
    }

    @Override
    public String toString() {
        return "ActorOption: actorType=" + actorTypeId + " option=" + optionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorOption that = (ActorOption) o;

        if (actorTypeId != null ? !actorTypeId.equals(that.actorTypeId) : that.actorTypeId != null) return false;
        return optionId != null ? optionId.equals(that.optionId) : that.optionId == null;

    }

    @Override
    public int hashCode() {
        int result = actorTypeId != null ? actorTypeId.hashCode() : 0;
        result = 31 * result + (optionId != null ? optionId.hashCode() : 0);
        return result;
    }
}
