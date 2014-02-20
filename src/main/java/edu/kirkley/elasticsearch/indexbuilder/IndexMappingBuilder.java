package edu.kirkley.elasticsearch.indexbuilder;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import org.elasticsearch.common.xcontent.XContentBuilder;

import edu.kirkley.elasticsearch.indexbuilder.field.BinaryIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.BooleanIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.ByteIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.DateIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.DoubleIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.FloatIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.IndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.ShortIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.StringIndexField;
import edu.kirkley.elasticsearch.indexbuilder.field.TokenCountIndexField;

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

    public IndexMappingBuilder addBinaryIndexField(String fieldName, boolean store, boolean analyzed) {
        BinaryIndexField newField = new BinaryIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addBooleanIndexField(String fieldName, boolean store, boolean analyzed) {
        BooleanIndexField newField = new BooleanIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addByteIndexField(String fieldName, boolean store, boolean analyzed) {
        ByteIndexField newField = new ByteIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addDoubleIndexField(String fieldName, boolean store, boolean analyzed) {
        DoubleIndexField newField = new DoubleIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addFloatIndexField(String fieldName, boolean store, boolean analyzed) {
        FloatIndexField newField = new FloatIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addShortIndexField(String fieldName, boolean store, boolean analyzed) {
        ShortIndexField newField = new ShortIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addStringIndexField(String fieldName, boolean store, boolean analyzed) {
        StringIndexField newField = new StringIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addTokenCountIndexField(String fieldName, boolean store, boolean analyzed) {
        TokenCountIndexField newField = new TokenCountIndexField(fieldName, store, analyzed);
        addIndexField(newField);
        return this;
    }

    public IndexMappingBuilder addDateIndexField(String fieldName, boolean store, boolean analyzed, String dateFormat) {
        DateIndexField newField = new DateIndexField(fieldName, store, analyzed, dateFormat);
        addIndexField(newField);
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
                for (Entry<String, Object> additionalInfo : indexField.getAdditionalInformation()) {
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
