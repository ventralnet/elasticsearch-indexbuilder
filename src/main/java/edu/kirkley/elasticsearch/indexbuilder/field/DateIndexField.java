package edu.kirkley.elasticsearch.indexbuilder.field;

public class DateIndexField extends IndexField {

    public DateIndexField(String indexName, String dateFormat, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
        addAdditionalInformation("format", dateFormat);
    }

    public String getDateFormat() {
        return getAdditionalInformation("format");
    }

    @Override
    public String getRawType() {
        return "date";
    }

}
