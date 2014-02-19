package edu.kirkley.elasticsearch.indexbuilder.field;

public class IntegerIndexField extends IndexField {

    public IntegerIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "integer";
    }

}
