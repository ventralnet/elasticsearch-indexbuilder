package edu.kirkley.elasticsearch.indexbuilder.field;

public class ShortIndexField extends IndexField {

    public ShortIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "short";
    }

}
