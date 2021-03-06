package gov.nist.toolkit.xdstools2.shared.command.request;

import gov.nist.toolkit.results.client.TestInstance;
import gov.nist.toolkit.xdstools2.shared.command.CommandContext;

import java.util.List;

/**
 * Created by onh2 on 11/10/16.
 */
public class GetTestResultsRequest extends CommandContext{
    private List<TestInstance> testIds;

    public GetTestResultsRequest(){}
    public GetTestResultsRequest(CommandContext context, List<TestInstance> testIds){
        copyFrom(context);
        this.testIds=testIds;
    }

    public List<TestInstance> getTestIds() {
        return testIds;
    }

    public void setTestIds(List<TestInstance> testIds) {
        this.testIds = testIds;
    }
}
