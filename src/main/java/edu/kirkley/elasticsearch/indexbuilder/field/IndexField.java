package edu.kirkley.elasticsearch.indexbuilder.field;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class IndexField {

    private String fieldName;

    private boolean store;

    private boolean analyzed;

    private Map<String, String> additionalInformation = new HashMap<String, String>();

    public IndexField(final String fieldName, final boolean store, final boolean analyzed) {
        this.store = store;
        this.analyzed = analyzed;
        this.fieldName = fieldName;
    }

    public boolean isStore() {
        return store;
    }

    public boolean isAnalyzed() {
        return analyzed;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void addAdditionalInformation(String key, String value) {
        additionalInformation.put(key, value);
    }

    public String getAdditionalInformation(String key) {
        return additionalInformation.get(key);
    }

    public Set<Entry<String, String>> getAdditionalInformation() {
        return additionalInformation.entrySet();
    }

    public abstract String getRawType();

}
