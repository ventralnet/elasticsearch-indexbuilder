package edu.kirkley.elasticsearch.indexbuilder;

import static org.mockito.Mockito.verify;

import org.elasticsearch.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.kirkley.elasticsearch.indexbuilder.analysis.Analyzer;

public class IndexBuilderUnitTests {

    @Mock
    private IndexSettingsBuilder mockSettingsBuilder;

    @Mock
    private Client mockClient;

    private IndexBuilder indexBuilder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        indexBuilder = new IndexBuilder(mockClient);
        indexBuilder.setIndexSettingsBuilder(mockSettingsBuilder);
    }

    @Test
    public void setNumberOfShardsPassesThroughToIndexSettingsBuilder() {
        indexBuilder.setNumberOfShards(10);
        verify(mockSettingsBuilder).setNumberOfShards(10);
    }

    @Test
    public void setNumberOfReplicasPassesThroughToIndexSettingsBuilder() {
        indexBuilder.setNumberOfReplicas(10);
        verify(mockSettingsBuilder).setNumberOfReplicas(10);
    }

    @Test
    public void getNumberOfShardsPassesThroughToIndexSettingsBuilder() {
        indexBuilder.getNumberOfShards();
        verify(mockSettingsBuilder).getNumberOfShards();
    }

    @Test
    public void setAnalyzerPassesThroughToIndexSettingsBuilder() {
        Analyzer analyzer = new Analyzer();
        indexBuilder.setAnalyzer(analyzer);
        verify(mockSettingsBuilder).setAnalyzer(analyzer);
    }

    @Test
    public void getAnalyzerPassesThroughToIndexSettingsBuilder() {
        indexBuilder.getAnalyzer();
        verify(mockSettingsBuilder).getAnalyzer();
    }

    @Test
    public void getNumberOfReplicasPassesThroughToIndexSettingsBuilder() {
        indexBuilder.getNumberOfReplicas();
        verify(mockSettingsBuilder).getNumberOfReplicas();
    }

    @Test(expected = IllegalStateException.class)
    public void buildThrowsExceptionIfIndexNameIsNotSet() {
        indexBuilder.setIndexName(null);
        indexBuilder.build();
    }

}
