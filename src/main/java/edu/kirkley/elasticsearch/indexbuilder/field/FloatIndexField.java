package edu.kirkley.elasticsearch.indexbuilder.field;

public class FloatIndexField extends IndexField {

    public FloatIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "float";
    }

}
