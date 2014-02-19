package edu.kirkley.elasticsearch.indexbuilder.field;

public class StringIndexField extends IndexField {

    public StringIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "string";
    }

}
