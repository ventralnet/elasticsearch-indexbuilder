package edu.kirkley.elasticsearch.indexbuilder;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import edu.kirkley.elasticsearch.indexbuilder.analysis.Analyzer;
import static org.junit.Assert.assertEquals;

public class IndexSettingsBuilderUnitTests {

    private IndexSettingsBuilder builder;
    
    @Before
    public void setup() {
        //TODO mock client
        builder = new IndexSettingsBuilder();
    }
    
    @Test
    public void setsCorrectNumberOfShards() {
        Integer expected = randomInteger();
        builder.setNumberOfShards(expected);
        assertEquals(expected,builder.getNumberOfShards());
    }
    
    @Test
    public void setsCorrectNumberOfReplicas() {
        Integer expected = randomInteger();
        builder.setNumberOfReplicas(expected);
        assertEquals(expected,builder.getNumberOfReplicas());
    }
    
    @Test
    public void setsCorrectAnalyzer() {
    	Analyzer analyzer = new Analyzer();
    	builder.setAnalyzer(analyzer);
    	assertEquals(analyzer, builder.getAnalyzer());
    }
    
    @Test
    public void setsAutoExpandReplicas() {
    	String expected = "0-all";
    	builder.setAutoExpandReplicas(expected);
    	assertEquals(expected, builder.getAutoExpandReplicas());
    }
    
    @Test
    public void setsIndexReadOnly() {
    	boolean expected = true;
    	builder.setIndexReadOnly(expected);
    	assertEquals(expected, builder.isIndexReadOnly());
    }
    
    @Test
    public void setWriteOperationsDisabled() {
    	boolean expected = true;
    	builder.setWriteOperationsDisabled(expected);
    	assertEquals(expected,builder.isWriteOperationsDisabled());
    }
    
    private int randomInteger() {
        return new Random().nextInt();
    }
    
}
