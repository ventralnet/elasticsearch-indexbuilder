package edu.kirkley.elasticsearch.indexbuilder;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;

public class IndexSettingsBuilder {

    private Integer numberOfShards;
    
    private Integer numberOfReplicas;

    private String indexName;
    
    private Client client;
    
    public IndexSettingsBuilder(Client client, String indexName) {
        this.client = client;
        this.indexName = indexName;
    }
 
    public IndexSettingsBuilder setName(final String indexName) {
        this.indexName = indexName;
        return this;
    }
    
    public String getIndexName() {
        return indexName;
    }
    
    public IndexSettingsBuilder setNumberOfShards(final int numberOfShards) {
        this.numberOfShards = numberOfShards;
        return this;
    }
    
    public IndexSettingsBuilder setNumberOfReplicas(final int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
        return this;
    }
    
    public Integer getNumberOfReplicas() {
        return numberOfReplicas;
    }
    
    public Integer getNumberOfShards() {
        return numberOfShards;
    }
    
    public CreateIndexRequest build() {
        Builder settingsBuilder = ImmutableSettings.builder();
        
        if (isNull(getIndexName())) {
            throw new IllegalStateException("You must set the index name.");
        }
        
        if (isNotNull(getNumberOfShards())) {
            settingsBuilder.put(IndexMetaData.SETTING_NUMBER_OF_SHARDS,getNumberOfShards());
        }
        if (isNotNull(getNumberOfReplicas())) {
            settingsBuilder.put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS,getNumberOfReplicas());
        }
        
        CreateIndexRequestBuilder builder = client.admin().indices().prepareCreate(getIndexName());
        builder.setSettings(settingsBuilder.build());
       
        builder.execute().actionGet();
        
        return builder.request();
    }
    
    private boolean isNull(Object o) {
        return o == null;
    }
    
    private boolean isNotNull(Object o) {
        return !isNull(o);
    }
    
}
