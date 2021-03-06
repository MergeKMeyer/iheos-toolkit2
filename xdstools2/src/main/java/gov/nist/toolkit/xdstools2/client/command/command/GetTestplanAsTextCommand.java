package gov.nist.toolkit.xdstools2.client.command.command;

import gov.nist.toolkit.xdstools2.client.util.ClientUtils;
import gov.nist.toolkit.xdstools2.shared.command.request.GetTestplanAsTextRequest;

/**
 * Created by onh2 on 11/10/16.
 */
public abstract class GetTestplanAsTextCommand extends GenericCommand<GetTestplanAsTextRequest,String>{
    @Override
    public void run(GetTestplanAsTextRequest var1) {
        ClientUtils.INSTANCE.getToolkitServices().getTestplanAsText(var1,this);
    }
}
