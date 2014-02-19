package edu.kirkley.elasticsearch.indexbuilder.field;

public class BooleanIndexField extends IndexField {

    public BooleanIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "boolean";
    }

}
