package edu.kirkley.elasticsearch.indexbuilder;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

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
    
    private int randomInteger() {
        return new Random().nextInt();
    }
    
}
