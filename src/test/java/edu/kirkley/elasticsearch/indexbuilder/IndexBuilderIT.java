package edu.kirkley.elasticsearch.indexbuilder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.elasticsearch.action.admin.cluster.state.ClusterStateRequest;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MetaData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.kirkley.elasticsearch.indexbuilder.util.MockElasticSearchServer;

public class IndexBuilderIT {

    private MockElasticSearchServer mockServer;

    private Client client;

    private IndexBuilder builder;

    private IndexBuilder builder2;

    private String testIndexName = "testindex";

    private String testIndexName2 = "testindex2";

    @Before
    public void setup() throws IOException {
        mockServer = new MockElasticSearchServer();
        shutdown(); // cleanup stale data
        mockServer = new MockElasticSearchServer();
        client = mockServer.getClient();
        builder = new IndexBuilder(client).setIndexName(testIndexName);
        builder2 = new IndexBuilder(client).setIndexName(testIndexName2);
    }

    @Test
    public void IndexSettingsBuilderProperlySetsNumberOfShards() {
        int expectedShards = 100;

        builder.setNumberOfShards(expectedShards);
        builder.build();

        assertNumberOfShardsEquals(expectedShards, testIndexName);
    }

    @Test
    public void IndexSettingsBuilderProperlySetsNumberOfReplicas() {
        int expectedReplicas = 100;

        builder.setNumberOfReplicas(expectedReplicas);
        builder.build();

        assertNumberOfReplicasEquals(expectedReplicas, testIndexName);
    }

    @Test
    public void IndexSettingsBuilderProperlySetsMetaDataOperationsEnabled() {
        boolean expected = true;

        builder.setMetaDataOperationsEnabled(expected);
        builder.build();

        assertMetaDataOptionsEnabled(expected, testIndexName);

        expected = false;

        builder2.setMetaDataOperationsEnabled(expected);
        builder2.build();

        assertMetaDataOptionsEnabled(expected, testIndexName2);
    }

    @Test
    public void IndexSettingsBuilderProperlySetsWriteOperationsDisabled() {
        boolean expected = true;

        builder.setWriteOperationsDisabled(expected);
        builder.build();

        assertWriteOperationsDisabled(expected, testIndexName);

        expected = false;

        builder2.setWriteOperationsDisabled(expected);
        builder2.build();

        assertWriteOperationsDisabled(expected, testIndexName2);
    }

    @Test
    public void IndexSettingsBuilderProperlySetsAutoExpandReplicas() {
        String expected = "0-all";

        builder.setAutoExpandReplicas(expected);
        builder.build();

        assertSetAutoExpandReplicas(expected, testIndexName);

        expected = "false";

        builder2.setAutoExpandReplicas(expected);
        builder2.build();

        assertSetAutoExpandReplicas(expected, testIndexName2);
    }

    private void assertSetAutoExpandReplicas(String expected, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expected, indexMetaData.getSettings().get(IndexMetaData.SETTING_AUTO_EXPAND_REPLICAS));
    }

    private void assertWriteOperationsDisabled(boolean expected, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expected, Boolean.valueOf(indexMetaData.getSettings().get(IndexMetaData.SETTING_BLOCKS_WRITE)));
    }

    private void assertMetaDataOptionsEnabled(boolean expected, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expected, Boolean.valueOf(indexMetaData.getSettings().get(IndexMetaData.SETTING_BLOCKS_METADATA)));
    }

    private void assertNumberOfShardsEquals(int expectedShards, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expectedShards, indexMetaData.getNumberOfShards());
    }

    private void assertNumberOfReplicasEquals(int expectedReplicas, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expectedReplicas, indexMetaData.getNumberOfReplicas());
    }

    private IndexMetaData getIndexMetadata(final String indexName) {
        ClusterStateRequest clusterStateRequest = Requests.clusterStateRequest().filterRoutingTable(true).filterNodes(true)
                .filteredIndices(new String[] { indexName });
        clusterStateRequest.listenerThreaded(false);

        ClusterStateResponse response = client.admin().cluster().state(clusterStateRequest).actionGet();
        MetaData metaData = response.getState().metaData();
        for (IndexMetaData indexMetaData : metaData) {
            if (indexMetaData.getIndex().equals(indexName)) {
                return indexMetaData;
            }
        }
        return null;
    }

    @After
    public void shutdown() throws IOException {
        mockServer.close();
    }

}
