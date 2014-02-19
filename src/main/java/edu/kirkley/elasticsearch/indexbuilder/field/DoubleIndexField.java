package edu.kirkley.elasticsearch.indexbuilder.field;

public class DoubleIndexField extends IndexField {

    public DoubleIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "double";
    }

}
