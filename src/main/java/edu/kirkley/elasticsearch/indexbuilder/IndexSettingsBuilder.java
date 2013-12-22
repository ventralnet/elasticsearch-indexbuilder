package edu.kirkley.elasticsearch.indexbuilder;

import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;

public class IndexSettingsBuilder {

    private Integer numberOfShards;
    
    private Integer numberOfReplicas;

    public IndexSettingsBuilder() {
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
    
    public Settings.Builder build() {
        Builder settingsBuilder = ImmutableSettings.builder();
        
        if (isNotNull(getNumberOfShards())) {
            settingsBuilder.put(IndexMetaData.SETTING_NUMBER_OF_SHARDS,getNumberOfShards());
        }
        if (isNotNull(getNumberOfReplicas())) {
            settingsBuilder.put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS,getNumberOfReplicas());
        }
        
        return settingsBuilder;
    }
    
    private boolean isNull(Object o) {
        return o == null;
    }
    
    private boolean isNotNull(Object o) {
        return !isNull(o);
    }
    
}
