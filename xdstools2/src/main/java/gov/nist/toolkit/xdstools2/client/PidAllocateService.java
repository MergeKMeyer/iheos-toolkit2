package gov.nist.toolkit.xdstools2.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("pidAllocate")
public interface PidAllocateService extends RemoteService {
	List<String> getAssigningAuthorities();
	String getNewPatientId(String assigningAuthority) throws Exception;
}