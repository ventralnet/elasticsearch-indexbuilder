package edu.kirkley.elasticsearch.indexbuilder.util;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;

public class MockElasticSearchServer implements Closeable {
    private final Node node;
    private final File dataDirectory;

    public MockElasticSearchServer() {
        this(new File("target", randomString()));
    }

    public MockElasticSearchServer(File dataDirectory) {
        this.dataDirectory = dataDirectory;

        ImmutableSettings.Builder elasticsearchSettings = ImmutableSettings.settingsBuilder().put(standaloneServerSettings());

        node = nodeBuilder().local(true).settings(elasticsearchSettings.build()).node();
    }

    public Client getClient() {
        return node.client();
    }

    public void close() throws IOException {
        node.close();
        deleteDataDirectory();
    }

    private Map<String, String> standaloneServerSettings() {
        Map<String, String> settings = new HashMap<String, String>();
        settings.put("http.enabled", "false");
        settings.put("path.data", dataDirectory.getAbsolutePath());
        return settings;
    }

    private static String randomString() {
        return UUID.randomUUID().toString();
    }

    private void deleteDataDirectory() throws IOException {
        FileUtils.deleteDirectory(dataDirectory);
    }

}
