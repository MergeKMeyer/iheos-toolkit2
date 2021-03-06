package gov.nist.toolkit.testkitutilities.client;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TestCollectionDefinitionDAO implements Serializable, IsSerializable {
    private String collectionID;
    private String collectionTitle;

    public TestCollectionDefinitionDAO() {}

    public TestCollectionDefinitionDAO(String collectionID, String collectionTitle) {
        this.collectionID = collectionID;
        this.collectionTitle = collectionTitle;
    }

    public String getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(String collectionID) {
        this.collectionID = collectionID;
    }

    public String getCollectionTitle() {
        return collectionTitle;
    }

    public void setCollectionTitle(String collectionTitle) {
        this.collectionTitle = collectionTitle;
    }

    public boolean isOption() { return collectionID.contains("_"); }

    static public List<TestCollectionDefinitionDAO> getNonOption(List<TestCollectionDefinitionDAO> all) {
        List<TestCollectionDefinitionDAO> out = new ArrayList<>();

        for (TestCollectionDefinitionDAO it : all) {
            if (it.isOption())
                continue;
            out.add(it);
        }

        return out;
    }
}
