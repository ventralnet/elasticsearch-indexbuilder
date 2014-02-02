package edu.kirkley.elasticsearch.indexbuilder;

import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;

import edu.kirkley.elasticsearch.indexbuilder.analysis.Analyzer;

public class IndexSettingsBuilder {

    private Integer numberOfShards;

    private Integer numberOfReplicas;

    private String autoExpandReplicas;

    private Boolean indexReadOnly;

    private Boolean writeOperationsDisabled;

    private Boolean metaDataOperationsEnabled;

    private Analyzer analyzer;

    public IndexSettingsBuilder() {
    }

    public IndexSettingsBuilder setMetaDataOperationsEnabled(boolean metaDataOperationsEnabled) {
        this.metaDataOperationsEnabled = metaDataOperationsEnabled;
        return this;
    }

    public Boolean isMetaDataOperationsEnabled() {
        return metaDataOperationsEnabled;
    }

    public IndexSettingsBuilder setNumberOfShards(final int numberOfShards) {
        this.numberOfShards = numberOfShards;
        return this;
    }

    public Boolean isWriteOperationsDisabled() {
        return writeOperationsDisabled;
    }

    public IndexSettingsBuilder setWriteOperationsDisabled(boolean writeOperationsDisabled) {
        this.writeOperationsDisabled = writeOperationsDisabled;
        return this;
    }

    public Boolean isIndexReadOnly() {
        return indexReadOnly;
    }

    public IndexSettingsBuilder setIndexReadOnly(boolean indexReadOnly) {
        this.indexReadOnly = indexReadOnly;
        return this;
    }

    public IndexSettingsBuilder setAutoExpandReplicas(String autoExpandReplicas) {
        this.autoExpandReplicas = autoExpandReplicas;
        return this;
    }

    public String getAutoExpandReplicas() {
        return autoExpandReplicas;
    }

    public IndexSettingsBuilder setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
        return this;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
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
            settingsBuilder.put(IndexMetaData.SETTING_NUMBER_OF_SHARDS, getNumberOfShards());
        }
        if (isNotNull(getNumberOfReplicas())) {
            settingsBuilder.put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, getNumberOfReplicas());
        }
        if (isNotNull(isMetaDataOperationsEnabled())) {
            settingsBuilder.put(IndexMetaData.SETTING_BLOCKS_METADATA, isMetaDataOperationsEnabled());
        }
        if (isNotNull(isWriteOperationsDisabled())) {
            settingsBuilder.put(IndexMetaData.SETTING_BLOCKS_WRITE, isWriteOperationsDisabled());
        }
        if (isNotNull(getAutoExpandReplicas())) {
            settingsBuilder.put(IndexMetaData.SETTING_AUTO_EXPAND_REPLICAS, getAutoExpandReplicas());
        }
        if (isNotNull(getAnalyzer())) {
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
