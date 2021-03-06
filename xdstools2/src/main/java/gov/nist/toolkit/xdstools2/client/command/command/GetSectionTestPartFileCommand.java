package gov.nist.toolkit.xdstools2.client.command.command;

import gov.nist.toolkit.session.client.logtypes.TestPartFileDTO;
import gov.nist.toolkit.xdstools2.client.util.ClientUtils;
import gov.nist.toolkit.xdstools2.shared.command.request.GetSectionTestPartFileRequest;

/**
 * Created by onh2 on 11/10/16.
 */
public abstract class GetSectionTestPartFileCommand extends GenericCommand<GetSectionTestPartFileRequest,TestPartFileDTO>{
    @Override
    public void run(GetSectionTestPartFileRequest var1) {
        ClientUtils.INSTANCE.getToolkitServices().getSectionTestPartFile(var1,this);
    }
}
