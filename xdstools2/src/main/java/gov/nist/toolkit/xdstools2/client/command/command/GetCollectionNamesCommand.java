package gov.nist.toolkit.xdstools2.client.command.command;

import gov.nist.toolkit.xdstools2.client.util.ClientUtils;
import gov.nist.toolkit.xdstools2.shared.command.request.GetCollectionRequest;

import java.util.Map;

/**
 * Created by onh2 on 11/7/16.
 */
public abstract class GetCollectionNamesCommand extends GenericCommand<GetCollectionRequest, Map<String, String>> {
    @Override
    public void run(GetCollectionRequest var1) {
        ClientUtils.INSTANCE.getToolkitServices().getCollectionNames(var1, this);
    }
}
