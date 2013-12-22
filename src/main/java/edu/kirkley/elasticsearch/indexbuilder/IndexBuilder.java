package edu.kirkley.elasticsearch.indexbuilder;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;

public class IndexBuilder {

    private IndexSettingsBuilder indexSettingsBuilder;

    private Client client;
    
    private String indexName;
    
    public IndexBuilder(final Client client) {
        indexSettingsBuilder = new IndexSettingsBuilder();
        this.client = client;
    }
    
    public String getIndexName() {
        return indexName;
    }
    
    public IndexBuilder setIndexName(String indexName) {
        this.indexName = indexName;
        return this;
    }
    
    public IndexBuilder setNumberOfShards(final int numberOfShards) {
        indexSettingsBuilder.setNumberOfShards(numberOfShards);
        return this;
    }

    public IndexBuilder setNumberOfReplicas(final int numberOfReplicas) {
        indexSettingsBuilder.setNumberOfReplicas(numberOfReplicas);
        return this;
    }

    public Integer getNumberOfReplicas() {
        return indexSettingsBuilder.getNumberOfReplicas();
    }

    public Integer getNumberOfShards() {
        return indexSettingsBuilder.getNumberOfShards();
    }

    public IndexBuilder setIndexSettingsBuilder(IndexSettingsBuilder indexSettingsBuilder) {
        this.indexSettingsBuilder = indexSettingsBuilder;
        return this;
    }
    
    public void build() {
        if (isNull(getIndexName())) {
            throw new IllegalStateException("You must set the index name.");
        }
        
        Settings.Builder settingsBuilder = indexSettingsBuilder.build();
        
        CreateIndexRequestBuilder builder = client.admin().indices().prepareCreate(getIndexName());
        builder.setSettings(settingsBuilder.build());
       
        builder.execute().actionGet();
    }
    
    private boolean isNull(Object o) {
        return o == null;
    }
    
}
