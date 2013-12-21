package edu.kirkley.elasticsearch.indexbuilder;

import java.io.IOException;

import org.elasticsearch.action.admin.cluster.state.ClusterStateRequest;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MetaData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.kirkley.elasticsearch.indexbuilder.util.MockElasticSearchServer;

import static org.junit.Assert.assertEquals;

public class IndexSettingsBuilderIT {

    private MockElasticSearchServer mockServer;
    
    private Client client;
    
    private IndexSettingsBuilder builder;
    
    private String testIndexName = "testindex";
    
    @Before
    public void setup() throws IOException {
        mockServer = new MockElasticSearchServer();
        shutdown(); //cleanup stale data
        mockServer = new MockElasticSearchServer();
        client = mockServer.getClient();
        builder = new IndexSettingsBuilder(client,testIndexName);
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
    
    private void assertNumberOfShardsEquals(int expectedShards, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expectedShards,indexMetaData.getNumberOfShards());
    }

    private void assertNumberOfReplicasEquals(int expectedReplicas, String indexName) {
        IndexMetaData indexMetaData = getIndexMetadata(indexName);
        assertEquals(expectedReplicas,indexMetaData.getNumberOfReplicas());
    }
    
    private IndexMetaData getIndexMetadata(final String indexName) {
        ClusterStateRequest clusterStateRequest = Requests.clusterStateRequest()
                .filterRoutingTable(true)
                .filterNodes(true)
                .filteredIndices(new String[]{indexName});
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
