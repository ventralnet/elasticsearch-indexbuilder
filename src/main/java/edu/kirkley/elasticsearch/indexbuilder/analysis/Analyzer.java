package edu.kirkley.elasticsearch.indexbuilder.analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Analyzer {

    private String name;

    private String type;

    private Map<String, Object> additionalOptions;

    public Analyzer() {
        additionalOptions = new HashMap<String, Object>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Iterator<Entry<String, Object>> additionalOptionsIterator() {
        return additionalOptions.entrySet().iterator();
    }

    public void putAdditionalOption(String name, Object value) {
        additionalOptions.put(name, value);
    }

}
