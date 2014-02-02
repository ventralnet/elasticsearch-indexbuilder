package edu.kirkley.elasticsearch.indexbuilder;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;

import edu.kirkley.elasticsearch.indexbuilder.analysis.Analyzer;

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

    public IndexBuilder setMetaDataOperationsEnabled(boolean metaDataOperationsEnabled) {
        indexSettingsBuilder.setMetaDataOperationsEnabled(metaDataOperationsEnabled);
        return this;
    }

    public boolean isMetaDataOperationsEnabled() {
        return indexSettingsBuilder.isMetaDataOperationsEnabled();
    }

    public boolean isWriteOperationsDisabled() {
        return indexSettingsBuilder.isWriteOperationsDisabled();
    }

    public IndexBuilder setWriteOperationsDisabled(boolean writeOperationsDisabled) {
        indexSettingsBuilder.setWriteOperationsDisabled(writeOperationsDisabled);
        return this;
    }

    public boolean isIndexReadOnly() {
        return indexSettingsBuilder.isIndexReadOnly();
    }

    public IndexBuilder setIndexReadOnly(boolean indexReadOnly) {
        indexSettingsBuilder.setIndexReadOnly(indexReadOnly);
        return this;
    }

    public IndexBuilder setAutoExpandReplicas(String autoExpandReplicas) {
        indexSettingsBuilder.setAutoExpandReplicas(autoExpandReplicas);
        return this;
    }

    public String getAutoExpandReplicas() {
        return indexSettingsBuilder.getAutoExpandReplicas();
    }

    public IndexBuilder setAnalyzer(final Analyzer analyzer) {
        indexSettingsBuilder.setAnalyzer(analyzer);
        return this;
    }

    public Analyzer getAnalyzer() {
        return indexSettingsBuilder.getAnalyzer();
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
