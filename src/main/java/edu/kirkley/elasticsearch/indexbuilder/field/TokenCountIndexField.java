package edu.kirkley.elasticsearch.indexbuilder.field;

public class TokenCountIndexField extends IndexField {

    public TokenCountIndexField(String indexName, boolean store, boolean analyzed) {
        super(indexName, store, analyzed);
    }

    @Override
    public String getRawType() {
        return "token_count";
    }

}
