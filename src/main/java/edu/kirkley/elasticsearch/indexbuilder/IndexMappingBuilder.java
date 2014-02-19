package edu.kirkley.elasticsearch.indexbuilder;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import org.elasticsearch.common.xcontent.XContentBuilder;

import edu.kirkley.elasticsearch.indexbuilder.field.IndexField;

public class IndexMappingBuilder {

    private Collection<IndexField> indexFields = new ArrayList<IndexField>();

    private String type;

    public IndexMappingBuilder(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public IndexMappingBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public IndexMappingBuilder addIndexField(IndexField indexField) {
        indexFields.add(indexField);
        return this;
    }

    public XContentBuilder build() {
        try {
            XContentBuilder mappingBuilder = jsonBuilder().startObject();
            mappingBuilder.startObject(getType());
            mappingBuilder.startObject("properties");
            for (IndexField indexField : indexFields) {
                mappingBuilder.startObject(indexField.getFieldName());
                mappingBuilder.field("type", indexField.getRawType());
                mappingBuilder.field("store", indexField.isStore());
                if (!indexField.isAnalyzed()) {
                    mappingBuilder.field("index", "not_analyzed");
                } else {
                    mappingBuilder.field("index", "analyzed");
                }
                for (Entry<String, String> additionalInfo : indexField.getAdditionalInformation()) {
                    mappingBuilder.field(additionalInfo.getKey(), additionalInfo.getValue());
                }
                mappingBuilder.endObject();
            }
            mappingBuilder.endObject();
            mappingBuilder.endObject();
            mappingBuilder.endObject();
            return mappingBuilder;
        } catch (Exception e) {
            throw new RuntimeException("Unable to build mapping", e);
        }
    }

}
