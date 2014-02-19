package edu.kirkley.elasticsearch.indexbuilder.field;

public class ByteIndexField extends IndexField {

    public ByteIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "byte";
    }

}
