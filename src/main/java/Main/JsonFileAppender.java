package Main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class JsonFileAppender implements Serializable {

    private final ObjectMapper jsonMapper = JsonMapper.builder().build();

    public JsonFileAppender() {
    }

    public void appendToArray(File jsonFile, Object value) throws IOException {
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Objects.requireNonNull(jsonFile);
        Objects.requireNonNull(value);
        if (jsonFile.isDirectory()) {
            throw new IllegalArgumentException("File can not be a directory!");
        } else {
            JsonNode node = this.readArrayOrCreateNew(jsonFile);
            ArrayNode array;
            if (((JsonNode)node).isArray()) {
                array = (ArrayNode)node;
                array.addPOJO(value);
            } else {
                array = this.jsonMapper.createArrayNode();
                array.add((JsonNode)node);
                array.addPOJO(value);
                node = array;
            }

            this.jsonMapper.writeValue(jsonFile, node);
        }
    }

    private JsonNode readArrayOrCreateNew(File jsonFile) throws IOException {
        return (JsonNode)(jsonFile.exists() && jsonFile.length() > 0L ? this.jsonMapper.readTree(jsonFile) : this.jsonMapper.createArrayNode());
    }
}
