package edu.kirkley.elasticsearch.indexbuilder.field;

public class DateIndexField extends IndexField {

    public DateIndexField(String indexName, boolean store, boolean analyzed, String dateFormat) {
        super(indexName, store, analyzed);
        addAdditionalInformation("format", dateFormat);
    }

    public String getDateFormat() {
        return (String) getAdditionalInformation("format");
    }

    @Override
    public String getRawType() {
        return "date";
    }

}
