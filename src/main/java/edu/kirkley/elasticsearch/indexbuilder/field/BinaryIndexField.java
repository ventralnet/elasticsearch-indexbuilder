package edu.kirkley.elasticsearch.indexbuilder.field;

public class BinaryIndexField extends IndexField {

    public BinaryIndexField(String fieldName, boolean store, boolean analyzed) {
        super(fieldName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "binary";
    }

}
